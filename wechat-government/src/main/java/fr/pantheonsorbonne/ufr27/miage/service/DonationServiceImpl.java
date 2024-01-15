package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.DonationGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.DonationDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.EDonation;
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
        fr.pantheonsorbonne.ufr27.miage.dto.Donation newDonation = new fr.pantheonsorbonne.ufr27.miage.dto.Donation(donation.getDescription(), donation.getRegionOfNeed(), donation.getMoneySupport(), donation.getTimeSupport(), donation.getTimeSupport(), donation.getMoneyGived(), donation.getTimeGived(), donation.getClotheGived());
        this.donationGateway.sendDonation(newDonation);

    }
    @Override
    @Transactional
    public void updateDonation(EDonation donation) {
        System.out.println("dfjce "+donation.getDonationId());
        Donation updateDonation=new Donation(donation.getDonationId(), donation.getDescription(), donation.getRegionOfNeed(), donation.getMoneySupport(), donation.getTimeSupport(), donation.getClotheSupport(), donation.getMoneyGived(), donation.getTimeGived(), donation.getClotheGived());
        this.donationDao.updateDonation(updateDonation);

    }

    @Override
    @Transactional
    public void deleteDonationService(int id){
        this.donationDao.deleteDonation(id);
    }


}