package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.camel.gateway.CompteGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchComptException;
import fr.pantheonsorbonne.ufr27.miage.dto.Booking;
import fr.pantheonsorbonne.ufr27.miage.dto.ETicket;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
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

    @Inject
    fr.pantheonsorbonne.ufr27.miage.camel.handler.checkUserHandler checkUserHandler;

    @Inject
    CompteGateway compteGateway;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        onException(NoSuchComptException.class)
                .handled(true)
                .setHeader("success", simple("false"))
                .setBody(simple("Le compte bancaire n existe pas : Mauvais Nom de Banque !"));

        from("sjms2:" + jmsPrefix + "MyBankSystem?exchangePattern=InOut")
                .unmarshal().json(TransfertArgent.class)
                .bean(compteGateway, "realizeOperation")
                .bean(checkUserHandler)
                .choice()
                    .when(header("success").isEqualTo(false))
                        .log("Le compte bancaire n existe pas : Mauvais Nom de Banque !")
                        .marshal().json()
                        .stop()
                    .otherwise()
                        .log("Versement termine : success")
                        .marshal().json()
                        .stop()
                .end()
                ;

    }

}
