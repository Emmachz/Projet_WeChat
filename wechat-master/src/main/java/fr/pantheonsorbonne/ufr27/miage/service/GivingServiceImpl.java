package fr.pantheonsorbonne.ufr27.miage.service;


import com.google.common.hash.Hashing;
import fr.pantheonsorbonne.ufr27.miage.dao.CustomerDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchTicketException;
import fr.pantheonsorbonne.ufr27.miage.dao.UserDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.DonationDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Donation;
import fr.pantheonsorbonne.ufr27.miage.dto.ETicket;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.dto.TicketType;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@ApplicationScoped
public class GivingServiceImpl implements GivingService {

    @Inject
    UserDAO userDAO;

    @Inject
    DonationDAO donationDAO;

    @Inject
    SeatPlacementService seatPlacementService;

    public String getKeyForTicket(Ticket ticket) {
        return Hashing.sha256().hashString(ticket.getId() + "" + ticket.getIdVenue().getId() + "" + ticket.getIdVendor().getId() + "MySuperSecret75013!", StandardCharsets.UTF_8).toString();
    }

    @Override
    @Transactional
    public String emitTicket(Giving giving) throws ExpiredTransitionalTicketException, NoSuchTicketException, CustomerNotFoundException.NoSeatAvailableException {

       /*enlever la quantity*/

        Donation donation = donationDAO.findDonation(giving.getDonationId());
        if (donation.getValidUntil().isBefore(Instant.now())) {
            throw new ExpiredTransitionalTicketException(eticket.getTransitionalTicketId());
        }
        donation = donationDAO.emitDonationForUser(giving.getDonationId(), customer);
        donation.setTicketKey(this.getKeyForTicket(donation));
        if (Objects.equals(eticket.getType(), TicketType.SEATING)) {
            donation.setSeatReference(seatPlacementService.bookSeat(donation.getIdVenue().getId()));
        }
        return donation.getTicketKey();


    }

    @Override
    @Transactional
    public void cleanUpTransitionalTicket(int transitionalTicketId) {
        donationDAO.(transitionalTicketId);
    }

}