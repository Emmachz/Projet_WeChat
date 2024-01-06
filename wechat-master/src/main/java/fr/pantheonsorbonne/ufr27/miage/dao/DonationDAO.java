package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Customer;
import fr.pantheonsorbonne.ufr27.miage.model.Ticket;

public interface DonationDAO {
    Ticket findDonation(int transitionalDonationId) throws NoSuchTicketException;

    Ticket emitDonationForUser(int transitionalTickerId, Customer customer);

    void removeTransitionalTicket(int transitionalTicketId);
}
