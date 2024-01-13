package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;
import fr.pantheonsorbonne.ufr27.miage.service.AlertService;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    //@ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.UserId")
    //int idUser;

    @Inject
    AlertService alertService;

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

        from("sjms2:topic:" + jmsPrefix)
                .unmarshal().json(Alert.class)
                .log("Clearning expired transitional ticket ${body}")
                .bean(alertGateway, "addAlert")
                .bean(alertGateway, "transfertAlert2");
                //.toD("sjms2:topic:alert${body.getAlertRegion()}" + jmsPrefix)
                //.marshal().json();

        from("direct:alerttransfert")
            .marshal().json()
                .choice()
                .when(header("headerRegion").isEqualTo("auvergne-rhone-alpes"))
                    .to("sjms2:topic:alertauvergne-rhone-alpes" + jmsPrefix)
                .when(header("headerRegion").isEqualTo("bourgogne-franche-comte"))
                    .to("sjms2:topic:alertbourgogne-franche-comte" + jmsPrefix)
                .when(header("headerRegion").isEqualTo("bretagne"))
                    .to("sjms2:topic:alertbretagne" + jmsPrefix)
                .when(header("headerRegion").isEqualTo("corse"))
                    .to("sjms2:topic:alertcorse" + jmsPrefix)
                .when(header("headerRegion").isEqualTo("centre-val-de-loire"))
                    .to("sjms2:topic:alertcentre-val-de-loire" + jmsPrefix)
                .when(header("headerRegion").isEqualTo("grand-est"))
                    .to("sjms2:topic:alertgrand-est" + jmsPrefix)
                .when(header("headerRegion").isEqualTo("hauts-de-france"))
                    .to("sjms2:topic:alerthauts-de-france" + jmsPrefix)
                .when(header("headerRegion").isEqualTo("ile-de-france"))
                 .to("sjms2:topic:alertile-de-france" + jmsPrefix)
                .when(header("headerRegion").isEqualTo("nouvelle-aquitaine"))
                    .to("sjms2:topic:alertnouvelle-aquitaine" + jmsPrefix)
                .when(header("headerRegion").isEqualTo("normandie"))
                    .to("sjms2:topic:alertnormandie" + jmsPrefix)
                .when(header("headerRegion").isEqualTo("occitanie"))
                    .to("sjms2:topic:alertoccitanie" + jmsPrefix)
                .when(header("headerRegion").isEqualTo("provence-alpes-cote-dazur"))
                    .to("sjms2:topic:alertprovence-alpes-cote-dazur" + jmsPrefix)
                .when(header("headerRegion").isEqualTo("pays-de-la-loire"))
                    .to("sjms2:topic:alertpays-de-la-loire" + jmsPrefix);


        from("sjms2:topic:alertauvergne-rhone-alpes" + jmsPrefix)
                .log("${body} + avergne-rhone-alpes");

        from("sjms2:topic:alertbourgogne-franche-comte" + jmsPrefix)
                .unmarshal().json(Alert.class)
                .log("${body} + bourgogne-franche-comte");

        from("sjms2:topic:alertbretagne" + jmsPrefix)
                .log("${body} + bretagne");

        from("sjms2:topic:alertcorse" + jmsPrefix)
                .log("${body}+ body CORSEEEEEEEréussiiiiiiiiiiii")
                //.unmarshal().json(Alert.class)
                .log("${body} + corse");

        from("sjms2:topic:alertcentre-val-de-loire" + jmsPrefix)
                .log("${body} + centre-val-de-loire");

        from("sjms2:topic:alertgrand-est" + jmsPrefix)
                .log("${body}+ body grandestttttttttt réussiiiiiiiiiiii")
                .log("${body} + grand-est");

        from("sjms2:topic:alerthauts-de-france" + jmsPrefix)
                .log("${body}+ body réussiiiiiiiiiiii")
                .log("${body} + hauts-de-franceeeeeeeeeeeeee");

        from("sjms2:topic:alertile-de-france" + jmsPrefix)
                .log("${body} + ile-de-france");

        from("sjms2:topic:alertnouvelle-aquitaine" + jmsPrefix)
                .log("${body} + nouvelle-aquitaine");

        from("sjms2:topic:alertnormandie" + jmsPrefix)
                .log("${body} + normandie");

        from("sjms2:topic:alertoccitanie" + jmsPrefix)
                .log("${body} + occitanie");

        from("sjms2:topic:alertprovence-alpes-cote-dazur" + jmsPrefix)
                .log("${body} + provence-alpes-cote-dazur");

        from("sjms2:topic:alertpays-de-la-loire" + jmsPrefix)
                .log("${body} + pays-de-la-loire");


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
