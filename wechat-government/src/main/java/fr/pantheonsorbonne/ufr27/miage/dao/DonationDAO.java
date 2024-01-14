package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import fr.pantheonsorbonne.ufr27.miage.model.Event;
import fr.pantheonsorbonne.ufr27.miage.model.Region;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;

public interface DonationDAO {

    @Transactional
    Collection<Donation> getDonations();

    @Transactional
    Donation addDonation(String RegionOfNeed, String description, double moneySupport, double timeSupport, double clotheSupport ) ;

    @Transactional
    void updateDonation(Donation updateDonation);

    @Transactional
    void deleteDonation(int id);
}
