package fr.pantheonsorbonne.ufr27.miage.service;

import com.google.common.hash.Hashing;
import fr.pantheonsorbonne.ufr27.miage.dao.CustomerDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchTicketException;
import fr.pantheonsorbonne.ufr27.miage.dao.TicketDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.ETicket;
import fr.pantheonsorbonne.ufr27.miage.dto.Gig;
import fr.pantheonsorbonne.ufr27.miage.dto.TicketType;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;
import fr.pantheonsorbonne.ufr27.miage.model.Customer;
import fr.pantheonsorbonne.ufr27.miage.model.Ticket;
import fr.pantheonsorbonne.ufr27.miage.model.Venue;
import fr.pantheonsorbonne.ufr27.miage.model.VenueLineUp;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

@ApplicationScoped
public class DonationServiceImpl implements TicketingService {


    @Inject
    CustomerDAO customerDAO;

    @Inject
    TicketDAO ticketDAO;

    @Inject
    SeatPlacementService seatPlacementService;

    public String getKeyForTicket(Ticket ticket) {
        return Hashing.sha256().hashString(ticket.getId() + "" + ticket.getIdVenue().getId() + "" + ticket.getIdVendor().getId() + "MySuperSecret75013!", StandardCharsets.UTF_8).toString();
    }

    @Override
    @Transactional
    public String emitTicket(ETicket eticket) throws ExpiredTransitionalTicketException, NoSuchTicketException, CustomerNotFoundException.NoSeatAvailableException {

        Customer customer = null;
        try {
            customer = customerDAO.findMatchingCustomer(eticket.getEmail());
        } catch (CustomerNotFoundException e) {
            customer = customerDAO.createNewCustomer(eticket.getFname(), eticket.getLname(), eticket.getEmail());
        }

        Ticket ticket = ticketDAO.findTicket(eticket.getTransitionalTicketId());
        if (ticket.getValidUntil().isBefore(Instant.now())) {
            throw new ExpiredTransitionalTicketException(eticket.getTransitionalTicketId());
        }
        ticket = ticketDAO.emitTicketForCustomer(eticket.getTransitionalTicketId(), customer);
        ticket.setTicketKey(this.getKeyForTicket(ticket));
        if (Objects.equals(eticket.getType(),TicketType.SEATING)) {
            ticket.setSeatReference(seatPlacementService.bookSeat(ticket.getIdVenue().getId()));
        }
        return ticket.getTicketKey();


    }

    @Override
    @Transactional
    public void cleanUpTransitionalTicket(int transitionalTicketId) {
        ticketDAO.removeTransitionalTicket(transitionalTicketId);
    }

    @Override
    public boolean validateTicket(Ticket t) {
        return this.getKeyForTicket(t).equals(t.getTicketKey());
    }

    @Override
    public Collection<Donation> getAvailableGigs(int idVendor) {
        Collection<Gig> gigs = new LinkedList<>();
        for (Venue venue : venueQuotaDAO.getQuotaForVendor(idVendor)) {
            StringBuilder builder = new StringBuilder();
            for (VenueLineUp lineUp : venue.getLineUp()) {
                builder.append(lineUp.getId().getArtist().getName());
                builder.append(" and ");
            }
            String allArtists = builder.substring(0, builder.length() - 5);
            gigs.add(new Gig(allArtists, venue.getLocation().getName(), venue.getVenueDate(), venue.getId().intValue()));
        }

        return gigs;
    }
}
