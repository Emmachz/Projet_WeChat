package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchTicketException;
import fr.pantheonsorbonne.ufr27.miage.dto.Booking;
import fr.pantheonsorbonne.ufr27.miage.dto.ETicket;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.model.Ticket;

public interface GivingService {
     String giveDonation(Giving giving)  throws ExpiredTransitionalTicketException, NoSuchTicketException, CustomerNotFoundException.NoSeatAvailableException;

    void cleanUpTransitionalTicket(int transitionalTicketId);

}