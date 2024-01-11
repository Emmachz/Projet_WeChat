package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.camel.gateway.VersementGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.handler.MessageResponsehandler;
import fr.pantheonsorbonne.ufr27.miage.camel.handler.PurchaseHandler;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.SellerNotRegisteredException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotExistingException;
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
    VersementGateway versementGateway;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        onException(SellerNotRegisteredException.class)
                .handled(true)
                .log("You have not been registered in WeChat. Create an account to sold with WeChat.");

        onException(UserNotExistingException.class)
                .handled(true)
                .log("This user do not exist in WeChat. The purchase will not be paid.");

        /*from("sjms2:" + jmsPrefix + "selling")//
                .log("purchased received: ${in.headers}")//
                .unmarshal().json(PurchaseDTO.class)//
                .bean(purchaseHandler, "init")
                .marshal().json();

        from("smjs2" + jmsPrefix + "confirm-purchase")
                .unmarshal().json(PurchaseConfirmation.class)//
                .bean(purchaseHandler, "confirm")
                .marshal().json()
                        .to("direct:ok");*/
                /*.process(new PurchaseInfoEnricher())
                .marshal().json();*/

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
