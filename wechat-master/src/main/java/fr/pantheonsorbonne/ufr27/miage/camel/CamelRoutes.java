package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.camel.gateway.AlertGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateway.GivingGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchUserException;
import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.AlertService;
import fr.pantheonsorbonne.ufr27.miage.service.MessageService;
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
    AlertService alertService;

    @Inject
    fr.pantheonsorbonne.ufr27.miage.camel.handler.GivingHandler givingHandler;

    @Inject
    MessageService messageService;

    @Inject
    AlertGateway alertGateway;
    @Inject
    GivingGateway givingGateway;


    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);
        onException(NoSuchUserException.class)
                .handled(true)
                .setHeader("success", simple("false"));

        onException(UserNotFoundException.NoExistUserException.class)
                .handled(true)
                .setHeader("success", simple("false"));

        from("sjms2:topic:" + jmsPrefix)
                .unmarshal().json(Alert.class)
                .log("Clearning expired transitional ticket ${body}")
                .bean(alertGateway, "addAlert")
                .bean(alertGateway, "transfertAlert2");
                //.toD("sjms2:topic:alert${body.getAlertRegion()}" + jmsPrefix)
                //.marshal().json();


        from("sjms2:" + jmsPrefix + "givingDonation")
                .unmarshal().json(Giving.class)
                .bean(givingGateway, "convertirGiving")
                .bean(givingHandler)
                .choice()
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
                                .to("sjms2:" + jmsPrefix + "sendGovernment")
                                .stop()
                            .otherwise()
                                .to("sjms2:" + jmsPrefix + "FailedGiving")
                                .stop()
                        .when(header("bank").isEqualTo("YesBank"))
                            .marshal().json()
                            .to("sjms2:" + jmsPrefix + "YesBankGiving?exchangePattern=InOut")
                            .choice()
                            .when(header("success").isEqualTo(true))
                                .to("sjms2:" + jmsPrefix + "sendGovernment")
                                .stop()
                            .otherwise()
                                .to("sjms2:" + jmsPrefix + "FailedGiving")
                                .stop()
                .when(header("typeGive").isEqualTo("time"))
                .bean(givingGateway, "giveTime")

                .when(header("typeGive").isEqualTo("clothe"))
                .bean(givingGateway, "giveClothe")
                .marshal().json()
                .end();


    }

}
