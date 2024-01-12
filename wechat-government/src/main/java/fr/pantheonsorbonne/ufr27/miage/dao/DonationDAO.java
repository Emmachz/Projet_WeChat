package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import fr.pantheonsorbonne.ufr27.miage.model.Help;

import java.util.Collection;
import java.util.List;
public interface DonationDAO {

    Collection<Donation> getDonations();

    Donation getDonationId(int id);

    void addDonation(Donation donation);

    Donation addDonation(int id, String descption, List<Help> helps);

    void deleteDonation(int id);
}