package fr.pantheonsorbonne.ufr27.miage.camel;

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
                .to("sjms2:" + jmsPrefix + "sendAlert");

        from ("direct:AlertAllRegion")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "sendAlertAllRegion");
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
