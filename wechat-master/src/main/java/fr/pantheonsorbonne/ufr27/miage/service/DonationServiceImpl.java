package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.DonationGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.TicketGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.Require;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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

    @Inject
    DonationGateway donationGateway;

    @Override
    @Transactional
    public Donation createDonation(Donation donation) throws UnsuficientQuotaForVenueException {
        try {
            VenueQuota vq = (VenueQuota) (em.createQuery("select d from Donation where d.id.venue.id=:venueId and q.id.vendor.id=:vendorId and q.seatingQuota>=:countSeating and q.standingQuota>=:countStanding")
                    .setParameter("vendorId", donation.getId())
                    .setParameter("venueId", donation.getDescription())
                    .setParameter("countStanding", donation.getRequires())

            Venue venue = em.find(Venue.class, donation.getVenueId());
            Vendor vendor = em.find(Vendor.class, donation.getVendorId());



        } catch (NonUniqueResultException | NoResultException e) {
            //throw new UnsuficientQuotaForVenueException();
        }
        return donation;


    }

    @Override
    @Transactional
    public void cancelDonation(int donationId) throws UnsuficientQuotaForVenueException {
        Donation donation = em.find(Donation.class, donationId);
        List<Donation> donationsToCancel = em.createQuery("SELECT d from Donation where d.idVenue.id=:venueId").setParameter("venueId", venueId).getResultList();
        for (Donation d : donationsToCancel) {
            donationGateway.cancelDonation(d);
            em.remove(d);
        }

    }




    protected Collection<Require> removeRequiresOfDonation(int artistId, Venue venue) {
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