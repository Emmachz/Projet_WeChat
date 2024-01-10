package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchTicketException;
import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.service.AlertService;
import fr.pantheonsorbonne.ufr27.miage.service.MessageService;
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
    MessageService messageService;

    @Inject
    AlertGateway alertGateway;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        from("sjms2:topic:" + jmsPrefix)
                .unmarshal().json(Alert.class)
                .log("Clearning expired transitional ticket ${body}")
                .bean(alertGateway, "addAlert")
                .bean(alertGateway, "transfertAlert")
                .marshal().json();


        from("direct:transfert")
                //queue
                .marshal().json()
                .log("TRANSFERTTTTTTTT ${body}")
                .to("direct:alertAll");

        from("direct:alertAll")
                .unmarshal().json(Alert.class)
                .log("Succèssssssssss alertAllllll ${body}")
                .marshal().json();
        //.log("${body().getAlertRegion()}")
                //.log(body().contains("haut de seine").toString())
                //.choice()
                //    .when(body().contains("haut de seine"))
                //        .to("direct:alertHautDeSeine" + jmsPrefix)
                //    .when(body().contains("urgent"))
                //        .to("direct:urgentMessages" + jmsPrefix)
                //    .otherwise()
                //       .to("direct:alertAll" + jmsPrefix)


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
