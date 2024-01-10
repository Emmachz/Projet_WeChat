package top.nextnet.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.dto.Booking;
import fr.pantheonsorbonne.ufr27.miage.dto.CancelationNotice;
import fr.pantheonsorbonne.ufr27.miage.dto.ETicket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);

        from("direct:alertHautDeSeine" + jmsPrefix)
                .unmarshal().json(Alert.class)
                .log("Succèssssssssss ${body}")
                .marshal().json();
    }

}
