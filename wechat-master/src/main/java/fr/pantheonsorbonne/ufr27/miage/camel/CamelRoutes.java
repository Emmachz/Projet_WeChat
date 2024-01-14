package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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
    AlertGateway alertGateway;
    @Inject
    GivingGateway givingGateway;
    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        from("sjms2:" + jmsPrefix + "sendAlert")
                .unmarshal().json(Alert.class)
                .bean(alertGateway, "addAlert")
                .bean(alertGateway, "transfertAlert");

        from("sjms2:" + jmsPrefix + "sendAlertAllRegion")
                .unmarshal().json(Alert.class)
                .bean(alertGateway, "addAlertAllRegion")
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

        from("sjms2:" + jmsPrefix + "givingDonation")
                .unmarshal().json(Giving.class)
                .bean(givingHandler)
                .choice()
                .when(header("typeGive").isEqualTo("money"))
                .bean(givingGateway, "giveMoney")
                .when(header("typeGive").isEqualTo("time"))
                .bean(givingGateway, "giveTime")
                .when(header("typeGive").isEqualTo("clothe"))
                .bean(givingGateway, "giveClothe")
                .marshal().json();
    }

    private static class ExpiredTransitionalTicketProcessor implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            //https://camel.apache.org/manual/exception-clause.html
            CamelExecutionException caused = (CamelExecutionException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);


            exchange.getMessage().setBody(((ExpiredTransitionalTicketException) caused.getCause()).getExpiredTicketId());
        }
    }
}
