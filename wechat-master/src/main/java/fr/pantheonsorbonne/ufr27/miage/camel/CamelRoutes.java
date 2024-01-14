package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.camel.gateway.AlertGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateway.DonationGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateway.GivingGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchUserException;
import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.dto.Donation;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.AlertService;
import fr.pantheonsorbonne.ufr27.miage.service.MessageService;
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
    fr.pantheonsorbonne.ufr27.miage.camel.handler.GivingHandler givingHandler;

    @Inject
    AlertService alertService;

    @Inject
    MessageService messageService;

    @Inject
    AlertGateway alertGateway;

    @Inject
    DonationGateway donationGateway;

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


        from("sjms2:" + jmsPrefix + "sendAlert")
                .unmarshal().json(Alert.class)
                .bean(alertGateway, "addAlert")
                .bean(alertGateway, "transfertAlert");


        from("direct:alerttransfert")
                .marshal().json()
                .process(exchange -> {
                    String headerRegion = exchange.getIn().getHeader("headerRegion", String.class);
                    String topicName = "sjms2:topic:alert" + headerRegion + jmsPrefix;
                    exchange.getIn().setHeader("topicName", topicName);
                })
                .toD("${header.topicName}");

        from("sjms2:" + jmsPrefix + "sendAlertAllRegion")
                .unmarshal().json(Donation.class)
                .bean(donationGateway, "addDonation")
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


        from("direct:donationTransfert")
                .marshal().json()
                .process(exchange -> {
                    String headerRegion = exchange.getIn().getHeader("headerRegion", String.class);
                    String topicName = "sjms2:topic:donation" + headerRegion + jmsPrefix;
                    exchange.getIn().setHeader("topicName", topicName);
                })
                .toD("${header.topicName}");





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


                from("direct:updateDonation")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "updateDonation");


    }

}
