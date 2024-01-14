package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.DonationGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.DonationDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.Collection;

@ApplicationScoped
public class DonationServiceImpl implements DonationService {

    @Inject
    DonationDAO donationDao;

    @Inject
    DonationGateway donationGateway;

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Collection<Donation> getDonationService(){

        return this.donationDao.getDonations();

    }
    @Override
    @Transactional
    public void addDonationService( String RegionOfNeed, String description, double moneySupport, double timeSupport, double clotheSupport ) {
        Donation donation = this.donationDao.addDonation(RegionOfNeed, description, moneySupport,  timeSupport, clotheSupport);
        this.donationGateway.sendDonation(donation);

    }
    @Override
    @Transactional
    public void updateDonation(fr.pantheonsorbonne.ufr27.miage.dto.Donation donation) {

        Donation updateDonation=new Donation(donation.getDonationId(), donation.getDescription(), donation.getRegionOfNeed(), donation.getMoneySupport(), donation.getTimeSupport(), donation.getClotheSupport(), donation.getMoneyGived(), donation.getTimeGived(), donation.getClotheGived());
        this.donationDao.updateDonation(updateDonation);

    }

    @Override
    @Transactional
    public void deleteDonationService(int id){
        this.donationDao.deleteDonation(id);
    }



    private boolean isValidRegion(String region) {
        String[] validRegions = {"auvergne-rhone-alpes", "bourgogne-franche-comte", "bretagne", "corse",
                "centre-val-de-loire", "grand-est", "hauts-de-france", "ile-de-france", "nouvelle-aquitaine",
                "normandie", "occitanie", "provence-alpes-cote-dazur", "pays-de-la-loire"};
        return Arrays.asList(validRegions).contains(region);
    }

}