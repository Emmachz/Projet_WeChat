package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Booking;
import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.exception.EventNotFoundException;
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
public class EventServiceImpl implements EventService{
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Event event(Aler alert) throws EventNotFoundException {
        try {


        } catch (NonUniqueResultException | NoResultException e) {
            throw new EventNotFoundException(booking.getVenueId());
        }
        return event;


    }




}
