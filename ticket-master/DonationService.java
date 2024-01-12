package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Donation;
import fr.pantheonsorbonne.ufr27.miage.exception.DonationNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Donation;

import java.util.Collection;

public interface DonationService {

    Donation donation(Donation donation) throws DonationNotFoundException;

    Collection<Donation> getDonationService();

    Donation getDonationServiceId(int id);

    Donation postDonationService( int id, String region);

    void addDonationService(Donation donation);

    void addDonationService(int id, String category, String region, String date, String hour, String description, String level, String status);

    void deleteDonationServiceId(int id);


}