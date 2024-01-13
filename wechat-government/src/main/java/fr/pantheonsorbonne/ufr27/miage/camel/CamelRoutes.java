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

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        from ("direct:Alert")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix);

        from("direct:alertHautDeSeine" + jmsPrefix)
                .unmarshal().json(Alert.class)
                .marshal().json();

        from("sjms2:topic:alerthaut-de-seine")
                .unmarshal().json(Alert.class)
                .marshal().json();

        from("sjms2:topic:alerthauts-de-france" + jmsPrefix)
                .log("testHAUTDESEINEEEEEEEE");

        from("sjms2:" + jmsPrefix + "sendGovernment")
                .unmarshal().json(Giving.class)
                .log("${body}");

    }
}
