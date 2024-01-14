package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.dto.Donation;
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
    CamelContext camelContext;

    @Inject
    DonationGateway donationGateway;


    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        from ("direct:Alert")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "sendAlert");

        from ("direct:Donation")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "sendDonation");

        from("sjms2:" + jmsPrefix + "updateDonation")
                .unmarshal().json(Donation.class)
                .bean(donationGateway, "updateDonation");


    }

}
