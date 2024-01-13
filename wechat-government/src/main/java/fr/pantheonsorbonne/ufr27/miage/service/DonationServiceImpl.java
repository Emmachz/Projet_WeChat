package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.AlertGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.DonationGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.DonationDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.EventDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.exception.EventNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import fr.pantheonsorbonne.ufr27.miage.model.Event;
import fr.pantheonsorbonne.ufr27.miage.model.Region;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class DonationServiceImpl implements DonationService {

    @Inject
    DonationDAO donationDao;

    @Inject
    DonationGateway donationGateway;

    @PersistenceContext
    EntityManager em;


    @Transactional
    public Collection<Donation> getDonationService(){

        return this.donationDao.getDonations();

    }
    @Transactional
    public String addDonationService( Collection<String> regions, String RegionOfNeed, String description ) throws UnsuficientQuotaForVenueException {
        Region regionOfNeedR = new Region();
        regionOfNeedR.setRegion(RegionOfNeed);

        for(String r : regions) {
            Region rRegion = new Region();
            rRegion.setRegion(r);
            if (!isValidRegion(rRegion) || !isValidRegion(regionOfNeedR)) {
                return "Erreur dans l'entrée de la région";
            }
            ListRegions.add(rRegion);

        }
        Donation donation = this.donationDao.addDonation(ListRegions, regionOfNeedR, description );
        this.donationGateway.sendDonation(donation);
        return "Event bien envoyé";

    }
/*
    @Transactional
    public void deleteEventServiceId(int id){
        this.eventdao.deleteEvent(id);
    }

 */

    private boolean isValidRegion(Region region) {
        String[] validRegions = {"auvergne-rhone-alpes", "bourgogne-franche-comte", "bretagne", "corse",
                "centre-val-de-loire", "grand-est", "hauts-de-france", "ile-de-france", "nouvelle-aquitaine",
                "normandie", "occitanie", "provence-alpes-cote-dazur", "pays-de-la-loire"};
        return Arrays.asList(validRegions).contains(region.getRegion());
    }

}