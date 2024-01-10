package top.nextnet.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Booking;
import fr.pantheonsorbonne.ufr27.miage.dto.CancelationNotice;
import fr.pantheonsorbonne.ufr27.miage.dto.ETicket;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
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

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.userLogin")
    String userLogin;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.user")
    String smtpUser;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.password")
    String smtpPassword;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.host")
    String smtpHost;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.port")
    String smtpPort;
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.from")
    String smtpFrom;

    @Inject
    CamelContext camelContext;


    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);

        from("direct:versement")
                .marshal().json()//, "onBookedResponseReceived"
                .to("sjms2:" + jmsPrefix + "TransfertArgent");

        from("direct:confirm-purchase")
                .marshal().json()//, "onBookedResponseReceived"
                .to("sjms2:" + jmsPrefix + "confirm-purchase");

        from("sjms2:topic:" + jmsPrefix + "versementSuccesEmetteur")
                .log("Message Versement succès ${body} ${headers}")
                .filter(header("login").isEqualTo(userLogin))
                .unmarshal().json(TransfertArgent.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        TransfertArgent transfertArgent = exchange.getMessage().getMandatoryBody(TransfertArgent.class);
                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("to", transfertArgent.getEmetteur().getUserEmail());
                        exchange.getMessage().setHeader("from", smtpFrom);
                        exchange.getMessage().setHeader("contentType", "text/html");
                        exchange.getMessage().setHeader("subject", "Versement Succès");
                        exchange.getMessage().setBody("Bonjour " + transfertArgent.getEmetteur().getUserName() +"," +
                                "\n\n Votre versement " + transfertArgent.getValue() + " euros à "+ transfertArgent.getReceveur().getUserName()+" a été mise en place avec succès." +
                                "\n\n Merci voutr confiance");
                    }
                })
                .log("Message Versement succès ${body} ${headers}")
                .to("smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword + "&contentType=");




    }
}
