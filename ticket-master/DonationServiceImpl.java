package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.DonationGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.DonationDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.DonationNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

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
    public Donation donation(Donation donation) throws DonationNotFoundException {
        try {


        } catch (NonUniqueResultException | NoResultException e) {
            throw new DonationNotFoundException(donation.getId());
        }
        return donation;

    }

    public Collection<Donation> getDonationService(){
        return this.donationDao.getDonations();
    }
    public Donation getDonationServiceId(int id){
        return this.donationDao.getDonationId(id);
    }

    public Donation postDonationService( int id, String region){
        return this.donationDao.postDonationRegion(id, region);
    }

    @Transactional
    public void addDonationService(Donation donation) {
        this.donationDao.addDonation(donation);
        this.donationGateway.sendDonation(donation);
    }

    @Transactional
    public void addDonationService(int id,  String description, Collection<Require> requires){
        this.donationDao.addDonation(id, description, requires);
    }

    @Transactional
    public void deleteDonationServiceId(int id){
        this.donationDao.deleteDonation(id);
    }



}