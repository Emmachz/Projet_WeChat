package fr.pantheonsorbonne.ufr27.miage.camel.gateway;

import fr.pantheonsorbonne.ufr27.miage.exception.DonationNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import fr.pantheonsorbonne.ufr27.miage.model.Region;
import fr.pantheonsorbonne.ufr27.miage.service.DonationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class DonationGateway {

    @Inject
    DonationService donationService;

    @Inject
    CamelContext camelContext;

    public void addDonation(fr.pantheonsorbonne.ufr27.miage.dto.Donation donation){
        this.donationService.addDonation(donation);
    }

    public void updateDonation(Donation donation){

        try (ProducerTemplate producer = camelContext.createProducerTemplate()) {
            producer.sendBody("direct:updateDonation", new fr.pantheonsorbonne.ufr27.miage.dto.Donation(donation.getId(), donation.getDescription(), donation.getRegionOfNeed(), donation.getMoneySupport(), donation.getTimeSupport(), donation.getClotheSupport(), donation.getMoneyGived(), donation.getTimeGived(), donation.getClotheGived()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

}
