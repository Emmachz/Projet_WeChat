package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import jakarta.transaction.Transactional;

import java.util.Collection;

public interface DonationService {
    public Collection<Donation> getDonationService();
    public void addDonationService(String RegionOfNeed, String description, double moneySupport, double timeSupport, double clotheSupport);
    public void updateDonation(fr.pantheonsorbonne.ufr27.miage.dto.Donation donation);

    @Transactional
    void deleteDonationService(int id);
}