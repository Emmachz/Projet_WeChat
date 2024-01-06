package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
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
    PurchaseHandler purshaseHandler;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        from("sjms2:" + jmsPrefix + "selling")//
                .log("purchased received: ${in.headers}")//
                .unmarshal().json(PurchaseDTO.class)//
                .bean(purshaseHandler, "init").marshal().json();

        /*onException(ExpiredTransitionalTicketException.class)
                .handled(true)
                .process(new ExpiredTransitionalTicketProcessor())
                .setHeader("success", simple("false"))
                .log("Clearning expired transitional ticket ${body}")
                .bean(ticketingService, "cleanUpTransitionalTicket");

        onException(UnsuficientQuotaForVenueException.class)
                .handled(true)
                .setHeader("success", simple("false"))
                .setBody(simple("Vendor has not enough quota for this venue"));


        onException(NoSuchTicketException.class)
                .handled(true)
                .setHeader("success", simple("false"))
                .setBody(simple("Ticket has expired"));

        onException(CustomerNotFoundException.NoSeatAvailableException.class)
                .handled(true)
                .setHeader("success", simple("false"))
                .setBody(simple("No seat is available"));


        from("sjms2:" + jmsPrefix + "booking?exchangePattern=InOut")//
                .log("ticker received: ${in.headers}")//
                .unmarshal().json(Booking.class)//
                .bean(bookingHandler, "book").marshal().json()
        ;


        from("sjms2:" + jmsPrefix + "ticket?exchangePattern=InOut")
                .unmarshal().json(ETicket.class)
                .bean(ticketingService, "emitTicket").marshal().json();


        from("direct:ticketCancel")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "cancellation");

    }

    private static class ExpiredTransitionalTicketProcessor implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            //https://camel.apache.org/manual/exception-clause.html
            CamelExecutionException caused = (CamelExecutionException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);


            exchange.getMessage().setBody(((ExpiredTransitionalTicketException) caused.getCause()).getExpiredTicketId());
        }*/
    }
}
