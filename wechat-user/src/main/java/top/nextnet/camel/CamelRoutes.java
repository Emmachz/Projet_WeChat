package top.nextnet.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.dto.Donation;
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

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.userRegion")
    String userRegion;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.userLogin")
    String userLogin;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.userMail")
    String userMail;

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
                .to("sjms2:" + jmsPrefix + "confirmation");

        from("sjms2:" + jmsPrefix + "versementSuccesEmetteur")
                .log("Message Versement succès ${body} ${headers}")
                .filter(header("emetteur").isEqualTo(userLogin))
                .unmarshal().json(TransfertArgent.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        TransfertArgent transfertArgent = exchange.getMessage().getMandatoryBody(TransfertArgent.class);
                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("to", transfertArgent.getEmetteur().userEmail());
                        exchange.getMessage().setHeader("from", smtpFrom);
                        exchange.getMessage().setHeader("contentType", "text/html");
                        exchange.getMessage().setHeader("subject", "Versement Success");
                        exchange.getMessage().setBody("Bonjour " + transfertArgent.getEmetteur().userName() +"," +
                                "\n\n Votre versement " + transfertArgent.getValue() + " euros a "+ transfertArgent.getReceveur().userName()+" a ete mise en place avec success." +
                                "\n\n Merci voutr confiance. \n\n WeChat");
                    }
                })
                .log("Message Versement succès ${body} ${headers}")
                .to("smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword + "&contentType=");

        from("direct:giving")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "givingDonation");

        from("sjms2:topic:alert" + userRegion + jmsPrefix)
                .unmarshal().json(Alert.class)
                .log("${body.getAlertDescription()}");


        from("sjms2:topic:alertcorse"+ jmsPrefix)
                .unmarshal().json()
                .log("${body}");

        from("sjms2:topic:donation" + userRegion + jmsPrefix)
                .unmarshal().json(Donation.class)
                .log(" Vous voulez faire un don pour : ${body.getDescription} ? ");


        from("sjms2:topic:donationcorse"+ jmsPrefix)
                .unmarshal().json(Donation.class)
                .log("Vous voulez faire un don pour : ${body.getDescription} ? ");

        from("sjms2:" + jmsPrefix + "FailedGiving")
                .log("Message Versement Failed ${body} ${headers}")
                .filter(header("login").isEqualTo(userLogin))
                .unmarshal().json(TransfertArgent.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("to",userMail);
                                exchange.getMessage().setHeader("from", smtpFrom);
                        exchange.getMessage().setHeader("contentType", "text/html");
                        exchange.getMessage().setHeader("subject", "Versement Failed");
                                exchange.getMessage().setBody("Bonjour " +userLogin +"," +
                                        "\n\n Votre versement n a pas ete mise en place avec success." +
                                        "\n\n Merci voutr confiance. \n\n WeChat");
                    }
                })
                .log("Message Versement Failed ${body} ${headers}")
                .to("smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword + "&contentType=");

    }
}
