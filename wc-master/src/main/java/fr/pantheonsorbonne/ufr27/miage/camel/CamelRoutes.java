package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.camel.gateway.AlertGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateway.DonationGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateway.GivingGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.handler.BankConverter;
import fr.pantheonsorbonne.ufr27.miage.camel.gateway.VersementGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.handler.MessageResponsehandler;
import fr.pantheonsorbonne.ufr27.miage.camel.handler.PurchaseHandler;
import fr.pantheonsorbonne.ufr27.miage.camel.handler.PurchaseEnricher;
import fr.pantheonsorbonne.ufr27.miage.dto.*;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    PurchaseHandler purchaseHandler;

    @Inject
    fr.pantheonsorbonne.ufr27.miage.camel.handler.VersementResponseHandler versementResponseHandler;

    @Inject
    fr.pantheonsorbonne.ufr27.miage.camel.handler.GivingHandler givingHandler;
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

    @Inject
    GivingGateway givingGateway;

    @Inject
    AlertGateway alertGateway;

    @Inject
    DonationGateway donationGateway;

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
/**
 * CAMEL MASTER : TRANSFERT D'ARGENT
 */
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
                .choice()
                .when(header("success").isEqualTo(true))
                    .to("sjms2:" + jmsPrefix + "YesBankSystem?exchangePattern=InOut")
                    .choice()
                    .when(header("success").isEqualTo(true))
                    .to("sjms2:" + jmsPrefix + "versementSuccesEmetteur")
                    .endChoice()
                    .otherwise()
                    .log("Versement Non Success : Erreur YesBankSystem ")
                    .endChoice()
                .otherwise()
                .log("Versement Non Success : Erreur MyBankSystem ")
                .endChoice()
                .endChoice()
                .end()
        ;


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
                .to("sjms2:" + jmsPrefix + "credit-seller?exchangePattern=InOut");//Crédite l'acheteur


        from("sjms2:" + jmsPrefix + "credit-seller?exchangePattern=InOut")
                .unmarshal().json(PurchaseDTO.class)
                .bean(bankConverter, "convertToCreditOperation")//Créer un crédit
                .to("sjms2:topic:" + jmsPrefix + "bank-credit?exchangePattern=InOut")//Envoyé vers la route banque
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
                .to("sjms2:topic:" + jmsPrefix + "bank-debit?exchangePattern=InOut")
                .process(exchange -> {
                    BankOperation debit = exchange.getMessage().getBody(BankOperation.class);
                    exchange.getMessage().setHeader("debit-success", debit.isComplete());
                })
                .bean(enricher, "findPurchaseInfosFromBankOperation")
                .end()
                .marshal().json();
/**
 * CAMEL MASTER : SEND ALERT
 */
        from("sjms2:" + jmsPrefix + "sendAlert")
                .unmarshal().json(Alert.class)
                .bean(alertGateway, "addAlert")
                .bean(alertGateway, "transfertAlert");

        from("sjms2:" + jmsPrefix + "sendAlertAllRegion")
                .unmarshal().json(Alert.class)
                .bean(alertGateway, "addAlert")
                .marshal().json()
                .process(exchange -> {
                    List<String> allRegions = Arrays.asList("auvergne-rhone-alpes", "bourgogne-franche-comte", "bretagne", "corse",
                            "centre-val-de-loire", "grand-est", "hauts-de-france", "ile-de-france", "nouvelle-aquitaine",
                            "normandie", "occitanie", "provence-alpes-cote-dazur", "pays-de-la-loire");
                    List<String> topicList = allRegions.stream()
                            .map(region -> "sjms2:topic:alert" + region + jmsPrefix)
                            .collect(Collectors.toList());
                    exchange.getIn().setHeader("topicList", topicList);
                })
                .recipientList(simple("${header.topicList}"));


        from("direct:alerttransfert")
                .marshal().json()
                .process(exchange -> {
                    String headerRegion = exchange.getIn().getHeader("headerRegion", String.class);
                    String topicName = "sjms2:topic:alert" + headerRegion + jmsPrefix;
                    exchange.getIn().setHeader("topicName", topicName);
                })
                .toD("${header.topicName}");
        /**
         * CAMEL MASTER : SEND A DONATION
         */
        from("sjms2:" + jmsPrefix + "sendDonation")
                .unmarshal().json(Donation.class)
                .bean(donationGateway, "addDonation")
                .process(exchange -> {
                    List<String> allRegions = Arrays.asList("auvergne-rhone-alpes", "bourgogne-franche-comte", "bretagne", "corse",
                            "centre-val-de-loire", "grand-est", "hauts-de-france", "ile-de-france", "nouvelle-aquitaine",
                            "normandie", "occitanie", "provence-alpes-cote-dazur", "pays-de-la-loire");;

                    String regionOnNeed =  ((Donation)exchange.getIn().getBody()).getRegionOfNeed();

                    List<String> topicList = allRegions.stream()
                            .filter(region -> !region.equals(regionOnNeed))
                            .map(region -> "sjms2:topic:donation" + region + jmsPrefix)
                            .collect(Collectors.toList());

                    exchange.getIn().setHeader("topicList", topicList);
                }).marshal().json()
                .recipientList(simple("${header.topicList}"));


        /**
        * CAMEL MASTER : GIVING DONATION
        */
        from("sjms2:" + jmsPrefix + "givingDonation")
                .unmarshal().json(Giving.class)
                .bean(givingGateway, "convertirGiving")
                .bean(givingHandler)
                .choice()
                    .when(header("typeGive").isEqualTo("time"))
                    .bean(givingGateway, "giveTime")
                    .stop()
                    .when(header("typeGive").isEqualTo("clothe"))
                    .bean(givingGateway, "giveClothe")
                    .stop()
                    .when(header("typeGive").isEqualTo("money"))
                        .choice()
                            .when(header("success").isEqualTo(true))
                                .bean(givingGateway, "giveMoney")
                                .marshal().json()
                                .to("sjms2:" + jmsPrefix + "sendGovernment")
                                .stop()
                            .when(header("success").isEqualTo("passBank"))
                                .choice()
                                    .when(header("bank").isEqualTo("MyBank"))
                                    .marshal().json()
                                    .to("sjms2:" + jmsPrefix + "MyBankGiving?exchangePattern=InOut")
                                        .choice()
                                        .when(header("success").isEqualTo(true))
                                        .unmarshal().json(Giving.class)
                                        .bean(givingGateway, "updateDonationAfterBank")
                                        .to("sjms2:" + jmsPrefix + "sendGovernment")
                                        .stop()
                                        .otherwise()
                                        .to("sjms2:" + jmsPrefix + "FailedGiving")
                                        .endChoice()
                                    .when(header("bank").isEqualTo("YesBank"))
                                    .marshal().json()
                                    .to("sjms2:" + jmsPrefix + "YesBankGiving?exchangePattern=InOut")
                                        .choice()
                                        .when(header("success").isEqualTo(true))
                                        .unmarshal().json(Giving.class)
                                        .bean(givingGateway, "updateDonationAfterBank")
                                        .to("sjms2:" + jmsPrefix + "sendGovernment")
                                        .stop()
                                        .otherwise()
                                        .to("sjms2:" + jmsPrefix + "FailedGiving")
                                        .endChoice()
                                .endChoice()
                        .endChoice()
                .endChoice()
                .end()
        ;


        from("direct:updateDonation")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "updateDonation");


    }
}
