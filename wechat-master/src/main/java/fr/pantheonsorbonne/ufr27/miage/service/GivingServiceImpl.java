package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
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
    public Giving giveMoney(Giving give) throws UnsuficientQuotaForVenueException {
        
        return null;
    }

    @Override
    @Transactional
    public Giving giveTime(Giving give) throws UnsuficientQuotaForVenueException {

       /* try {


            Help vq = (VenueQuota) (em.createQuery("select h from Donation d, Donation_Help dp, Help h  where d.id.id=:venueId and q.id.vendor.id=:vendorId and q.seatingQuota>=:countSeating and q.standingQuota>=:countStanding")
=======
        /*try {

            VenueQuota vq = (VenueQuota) (em.createQuery("select q from Re q where q.id.venue.id=:venueId and q.id.vendor.id=:vendorId and q.seatingQuota>=:countSeating and q.standingQuota>=:countStanding")
>>>>>>> a4244aa7616f8a31d39ca3cf452225aea48efc06
                    .setParameter("vendorId", give.getVendorId())
                    .setParameter("venueId", give.getVenueId())
                    .setParameter("countStanding", give.getStandingTicketsNumber())
                    .setParameter("countSeating", give.getSeatingTicketsNumber()).getSingleResult());
            vq.setSeatingQuota(vq.getSeatingQuota() - give.getSeatingTicketsNumber());
            vq.setStandingQuota(vq.getStandingQuota() - give.getStandingTicketsNumber());

            Venue venue = em.find(Venue.class, give.getVenueId());
            Vendor vendor = em.find(Vendor.class, give.getVendorId());

        } catch (NonUniqueResultException | NoResultException e) {
            throw new UnsuficientQuotaForVenueException(give.getVenueId());
        }
        return give;*/
            return null;

    }

    @Override
    @Transactional
    public Giving giveClothe(Giving give) throws UnsuficientQuotaForVenueException {
        return null;
    }
}