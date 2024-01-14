package top.nextnet.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.dto.Donation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.userRegion")
    String userRegion;
    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);
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

    }
}
