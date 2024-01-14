package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import fr.pantheonsorbonne.ufr27.miage.model.Event;
import fr.pantheonsorbonne.ufr27.miage.service.AlertService;
import fr.pantheonsorbonne.ufr27.miage.service.DonationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@ApplicationScoped
public class DonationGateway {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Inject
    DonationService donationService;

    public void sendDonation(Donation donation) {
        try (ProducerTemplate producer = camelContext.createProducerTemplate()) {
            producer.sendBody("direct:Donation", donation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateDonation(fr.pantheonsorbonne.ufr27.miage.dto.Donation donation){
        this.donationService.updateDonation(donation);
    }




}
