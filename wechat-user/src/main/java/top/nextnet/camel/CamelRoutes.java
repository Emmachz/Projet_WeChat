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
        from("direct:giving")
                .log("dede")
                .marshal().json()//, "onBookedResponseReceived"
                .log("dede")
                .to("sjms2:" + jmsPrefix + "givingDonation");


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

    }

        /*from("direct:confirm-purchase")
                .marshal().json()//, "onBookedResponseReceived"
                .to("sjms2:" + jmsPrefix + "confirm-purchase");

         */

        /*from("direct:cli")//
                .marshal().json()//, "onBookedResponseReceived"
                .to("sjms2:" + jmsPrefix + "booking?exchangePattern=InOut")//
                .choice()
                .when(header("success").isEqualTo(false))
                .setBody(simple("not enough quota for this vendor"))
                .bean(eCommerce, "showErrorMessage").stop()
                .otherwise()
                .unmarshal().json(Booking.class)
                .bean(BookingResponseHandler)
                .log("response received ${in.body}")
                .bean(ticketingService, "fillTicketsWithCustomerInformations")
                .split(body())
                .marshal().json(ETicket.class)
                .to("sjms2:" + jmsPrefix + "ticket?exchangePattern=InOut")
                .choice()
                .when(header("success").isEqualTo(false))
                .bean(eCommerce, "showErrorMessage").stop()
                .otherwise()
                .bean(ticketingService, "notifyCreatedTicket");


        from("sjms2:topic:" + jmsPrefix + "cancellation")
                .log("cancellation notice ${body} ${headers}")
                .filter(header("vendorId").isEqualTo(vendorId))

                .unmarshal().json(CancelationNotice.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        CancelationNotice notice = exchange.getMessage().getMandatoryBody(CancelationNotice.class);
                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("to", notice.getEmail());
                        exchange.getMessage().setHeader("from", smtpFrom);
                        exchange.getMessage().setHeader("contentType", "text/html");
                        exchange.getMessage().setHeader("subject", "cancellation notice for venue");
                        exchange.getMessage().setBody("Dear Customer,\n\n Venue for your ticket " + notice.getTicketId() + " has been cancelled.\n\n Contact vendor for refund");
                    }
                })
                .log("cancellation notice ${body} ${headers}")
                .to("smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword + "&contentType=")
                .bean(eCommerce, "showErrorMessage");
            */


}
