package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.camel.gateway.BankConverter;
import fr.pantheonsorbonne.ufr27.miage.camel.gateway.VersementGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.handler.MessageResponsehandler;
import fr.pantheonsorbonne.ufr27.miage.camel.handler.PurchaseHandler;
import fr.pantheonsorbonne.ufr27.miage.camel.processor.PurchaseEnricher;
import fr.pantheonsorbonne.ufr27.miage.dto.BankOperation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseConfirmation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.AlreadyPaidPurchaseException;
import fr.pantheonsorbonne.ufr27.miage.exception.SellerNotRegisteredException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotExistingException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.WalletService;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    PurchaseHandler purchaseHandler;

    @Inject
    fr.pantheonsorbonne.ufr27.miage.camel.handler.VersementResponseHandler versementResponseHandler;

    @Inject
    MessageResponsehandler messageResponsehandler;

    @Inject
    WalletService walletHandler;

    @Inject
    VersementGateway versementGateway;

    @Inject
    PurchaseEnricher enricher;

    @Inject
    CamelContext camelContext;

    @Inject
    BankConverter bankConverter;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        onException(SellerNotRegisteredException.class)
                .handled(true)
                .log("You have not been registered in WeChat. Create an account to sell with WeChat.");

        onException(UserNotExistingException.class)
                .handled(true)
                .log("This user do not exist in WeChat. The purchase will not be paid.");

        onException(UserNotFoundException.NoExistUserException.class)
                .handled(true)
                .log("User does not exist in WeChat.");

        onException(AlreadyPaidPurchaseException.class)
                .handled(true)
                .log("This purchase has already been confirmed.");

        from("sjms2:" + jmsPrefix + "selling")//Reception de la vente d'un vendeur
                .unmarshal().json(PurchaseDTO.class)//
                .bean(purchaseHandler, "init");//Stocke l'achat en base de donnée et attend la confirmation d'un acheteur

        from("sjms2:" + jmsPrefix + "confirmation")//Reception de la confirmation de l'acheteur
                .unmarshal().json(PurchaseConfirmation.class)//
                .bean(purchaseHandler, "confirm") //Vérifie les infos prouvant l'identité de l'acheteur
                .bean(enricher, "findPurchaseInfosFromConfirmation")//Récupère les informations sur la vente
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "debit-user?exchangePattern=InOut")//Débite l'acheteur
                .unmarshal().json(PurchaseDTO.class)
                .choice()
                .when(header("debit-success").isEqualTo(true))//Le débit a bien eu lieu
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "credit-seller?exchangePattern=InOut")//Crédite l'acheteur
                .unmarshal().json(PurchaseDTO.class)
                .choice()
                .when(header("credit-success").isEqualTo(false))//Le crédit n'a pas eu lieu
                .bean(bankConverter, "convertToDebitOperation")//Reformation du débit initial
                .log("send back to user")
                .to("sjms2:" + jmsPrefix + "bank-credit?exchangePattern=InOut")//Qui sera finalement crédité à l'acheteur après l'échec du débit
                .log("Error occurred during creditation of the seller. User get his money back.");


        from("sjms2:" + jmsPrefix + "credit-seller?exchangePattern=InOut")
                .unmarshal().json(PurchaseDTO.class)
                .bean(bankConverter, "convertToCreditOperation")//Créer un crédit
                .to("sjms2:" + jmsPrefix + "bank-credit?exchangePattern=InOut")//Envoyé vers la route banque
                .process(exchange -> {
                    BankOperation credit = exchange.getMessage().getBody(BankOperation.class);
                    exchange.getMessage().setHeader("credit-success", credit.isComplete()); //Permet de donner l'info de la créditation ou non
                })
                .bean(enricher, "findPurchaseInfosFromBankOperation") //Récupère l'achat initial
                .marshal().json();

        from("sjms2:" + jmsPrefix + "debit-user?exchangePattern=InOut")
                .unmarshal().json(PurchaseDTO.class)//
                .bean(walletHandler, "debit")
                .process(exchange -> {
                    PurchaseDTO purchase = exchange.getMessage().getBody(PurchaseDTO.class);
                    exchange.getMessage().setHeader("debit-success", purchase.isDebitOk());
                })
                .choice()
                .when(header("debit-success").isEqualTo(false))
                .bean(bankConverter, "convertToDebitOperation")
                .to("sjms2:" + jmsPrefix + "bank-debit?exchangePattern=InOut")
                .process(exchange -> {
                    BankOperation debit = exchange.getMessage().getBody(BankOperation.class);
                    exchange.getMessage().setHeader("debit-success", debit.isComplete());
                })
                .bean(enricher, "findPurchaseInfosFromBankOperation")
                .end()
                .marshal().json();


        from("sjms2:" + jmsPrefix + "TransfertArgent")
                .unmarshal().json(TransfertArgent.class)
                .bean(versementGateway, "findTwoUsersVersement")
                .bean(versementResponseHandler)
                .choice()
                .when(header("success").isEqualTo(true))
                .bean(versementGateway, "realizeVersementWallet")
                .bean(messageResponsehandler)
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "versementSuccesEmetteur")
                .stop()
                .otherwise()
                .bean(versementGateway, "sendInfosToBank")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "MyBankSystem?exchangePattern=InOut")
                .log("${headers}")
                .choice()
                .when(header("success").isEqualTo(true))
                .to("sjms2:" + jmsPrefix + "YesBankSystem?exchangePattern=InOut")
                .choice()
                .when(header("success").isEqualTo(true))
                .to("sjms2:" + jmsPrefix + "versementSuccesEmetteur")
                .stop()
                .otherwise()
                .log("Versement Non Success : Erreur YesBankSystem ")
                .otherwise()
                .log("Versement Non Success : Erreur MyBankSystem ")
                .end()
        ;




    }
}
