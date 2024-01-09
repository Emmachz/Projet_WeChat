package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Donation;
import fr.pantheonsorbonne.ufr27.miage.model.Customer;
import fr.pantheonsorbonne.ufr27.miage.model.Ticket;

public interface DonationDAO {
    Donation findDonation(int transitionalDonationId) throws NoSuchTicketException;

    Donation giveDonation(int transitionalDonationId, String typeGive, int quantity);

    void removeDonation(int donationId);
}