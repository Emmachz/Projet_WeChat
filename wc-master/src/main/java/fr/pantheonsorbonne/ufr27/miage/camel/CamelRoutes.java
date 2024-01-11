package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.camel.gateway.PaymentsGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateway.VersementGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.handler.PurchaseHandler;
import fr.pantheonsorbonne.ufr27.miage.camel.processor.PurchaseInfoEnricher;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseConfirmation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.SellerNotRegisteredException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotExistingException;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.CamelContext;
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
    PurchaseHandler purchaseHandler;

    @Inject
    fr.pantheonsorbonne.ufr27.miage.camel.handler.VersementResponseHandler versementResponseHandler;

    @Inject
    fr.pantheonsorbonne.ufr27.miage.camel.handler.EmetteurResponsehandler emetteurResponsehandler;

    @Inject
    fr.pantheonsorbonne.ufr27.miage.camel.handler.ReceveurResponsehandler receveurResponsehandler;

    @Inject
    VersementGateway versementGateway;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        onException(SellerNotRegisteredException.class)
                .handled(true)
                .log("You have not been registered in WeChat. Create an account to sold with WeChat.");

        onException(UserNotExistingException.class)
                .handled(true)
                .log("This user do not exist in WeChat. The purchase will not be paid.");

        /*from("sjms2:" + jmsPrefix + "selling")//
                .log("purchased received: ${in.headers}")//
                .unmarshal().json(PurchaseDTO.class)//
                .bean(purchaseHandler, "init")
                .marshal().json();

        from("smjs2" + jmsPrefix + "confirm-purchase")
                .unmarshal().json(PurchaseConfirmation.class)//
                .bean(purchaseHandler, "confirm")
                .marshal().json()
                        .to("direct:ok");*/
                /*.process(new PurchaseInfoEnricher())
                .marshal().json();*/

        from("sjms2:" + jmsPrefix + "TransfertArgent")
                .unmarshal().json(TransfertArgent.class)
                .bean(versementGateway, "findTwoUsersVersement")
                .bean(versementResponseHandler)
                .choice()
                .when(header("success").isEqualTo(true))
                .bean(versementGateway, "realizeVersementWallet")
                .bean(emetteurResponsehandler)
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "versementSuccesEmetteur")
                .stop()
                .otherwise()
                .bean(versementGateway, "sendInfosToBank")
                .marshal().json()
                .multicast()
                .parallelProcessing()
                .to("sjms2:" + jmsPrefix + "MyBankSystem", "sjms2:" + jmsPrefix + "YesBankSystem")



        ;




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
