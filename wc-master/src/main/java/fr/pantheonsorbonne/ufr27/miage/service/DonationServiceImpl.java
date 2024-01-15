package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.DonationDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class DonationServiceImpl implements DonationService {
    @PersistenceContext
    EntityManager em;

    @Inject
    DonationDAO donationDAO;

    @Override
    public void addDonation(fr.pantheonsorbonne.ufr27.miage.dto.Donation donation){
        this.donationDAO.addDonation(donation);
    }


}