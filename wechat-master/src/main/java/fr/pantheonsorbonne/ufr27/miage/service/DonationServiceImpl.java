package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.*;

@ApplicationScoped
public class DonationServiceImpl implements DonationService {

    @PersistenceContext
    EntityManager em;


    @Override
    @Transactional
    public Donation createDonation(Donation donation) throws UnsuficientQuotaForVenueException {
        try {
            VenueQuota vq = (VenueQuota) (em.createQuery("select q from  q where q.id.venue.id=:venueId and q.id.vendor.id=:vendorId and q.seatingQuota>=:countSeating and q.standingQuota>=:countStanding")
                    .setParameter("vendorId", booking.getVendorId())
                    .setParameter("venueId", booking.getVenueId())
                    .setParameter("countStanding", booking.getStandingTicketsNumber())
                    .setParameter("countSeating", booking.getSeatingTicketsNumber()).getSingleResult());
            vq.setSeatingQuota(vq.getSeatingQuota() - booking.getSeatingTicketsNumber());
            vq.setStandingQuota(vq.getStandingQuota() - booking.getStandingTicketsNumber());

            Venue venue = em.find(Venue.class, booking.getVenueId());
            Vendor vendor = em.find(Vendor.class, booking.getVendorId());



        } catch (NonUniqueResultException | NoResultException e) {
            throw new UnsuficientQuotaForVenueException(booking.getVenueId());
        }
        return booking;


    }

    @Override
    @Transactional
    public void cancelDonation(int donationId) throws UnsuficientQuotaForVenueException {
        Donation donation = em.find(Donation.class, donationId);
        List<Donation> donationsToCancel = em.createQuery("SELECT d from V t where t.idVenue.id=:venueId").setParameter("venueId", venueId).getResultList();
        for (Donation d : donationsToCancel) {
            ticketGateway.cancelTicket(t);
            em.remove(d);
        }

    }




    protected Collection<VenueLineUp> removeVenuesLineup(int artistId, Venue venue) {
        Collection<VenueLineUp> venueLineupToRemove = new ArrayList<>();
        for (VenueLineUp lineup : venue.getLineUp()) {
            if (lineup.getId().getArtist().getIdArtist().equals(artistId)) {
                venueLineupToRemove.add(lineup);
            }
        }

        for (VenueLineUp lu : venueLineupToRemove) {
            em.remove(lu);
        }

        return venueLineupToRemove;
    }
}