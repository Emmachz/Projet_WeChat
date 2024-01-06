package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.model.Ticket;
import fr.pantheonsorbonne.ufr27.miage.model.Vendor;
import fr.pantheonsorbonne.ufr27.miage.model.Venue;
import fr.pantheonsorbonne.ufr27.miage.model.VenueQuota;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class GivingServiceImpl implements GivingService {

    @PersistenceContext
    EntityManager em;


    @Override
    @Transactional
    public Giving give(Giving giving) throws UnsuficientQuotaForVenueException {
        try {
            VenueQuota vq = (VenueQuota) (em.createQuery("select q from Giving q where q.id.regionOfUser.id=:regionOfUser and q.id.donation.id=:donationId and q.id.typeGive.id=:typeGive and q.id.quantity.id=:quantity")
                    .setParameter("donationId", giving.getDonationId())
                    .setParameter("regionOfUser", giving.getRegionOfUser())
                    .setParameter("typeGive", giving.getTypeGive())
                    .setParameter("quantity", giving.getQuantity()));

            Venue venue = em.find(Venue.class, booking.getVenueId());
            Vendor vendor = em.find(Vendor.class, booking.getVendorId());


            for (int i = 0; i < booking.getStandingTicketsNumber(); i++) {
                Ticket ticket = new Ticket();
                ticket.setValidUntil(Instant.now().plus(10, ChronoUnit.HOURS));
                ticket.setIdVendor(vendor);
                ticket.setIdVenue(venue);
                em.persist(ticket);
                booking.getStandingTransitionalTicket().add(ticket.getId());

            }

            for (int i = 0; i < booking.getSeatingTicketsNumber(); i++) {
                Ticket ticket = new Ticket();
                ticket.setValidUntil(Instant.now().plus(10, ChronoUnit.MINUTES));
                ticket.setIdVendor(vendor);
                ticket.setIdVenue(venue);
                ticket.setSeatReference("");
                em.persist(ticket);

                booking.getSeatingTransitionalTicket().add(ticket.getId());

            }

        } catch (NonUniqueResultException | NoResultException e) {
            throw new UnsuficientQuotaForVenueException(booking.getVenueId());
        }
        return booking;


    }
}
