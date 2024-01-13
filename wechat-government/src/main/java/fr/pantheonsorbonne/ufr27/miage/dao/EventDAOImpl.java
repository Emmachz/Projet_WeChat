package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.EventNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Event;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class EventDAOImpl implements EventDAO {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Collection<Event> getEvent() {
        return em.createQuery("SELECT event FROM Event event", Event.class).getResultList();
    }

    @Override
    @Transactional
    public Event getEventId(int id) {
        try {
            return em.createQuery("SELECT event FROM Event event WHERE idEvent = :id", Event.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
            //throw new EventNotFoundException(alert.getAlertId());
            return null;
        }
    }

    @Override
    @Transactional
    public Event postEventRegion(int id, String region) {
        Event event = em.find(Event.class, id);
        if (event != null) {
            event.setRegion(region);
            em.persist(event);
        }
        return event;
    }
    @Override
    @Transactional
    public void addEvent(Event event) {
        if (!isEventExists(event.getIdEvent())) {
            Event attachedEvent = em.merge(event);
            em.persist(attachedEvent);
        } else {
            System.out.println("Erreur : L'événement avec l'ID " + event.getIdEvent() + " existe déjà.");
        }
    }

    @Override
    @Transactional
    public Event addEvent(int id, String category, String region, String date, String hour, String description) {
        Event newEvent = new Event();
        newEvent.setIdEvent(id);
        newEvent.setCategory(category);
        newEvent.setRegion(region);
        newEvent.setDate(date);
        newEvent.setHour(hour);
        newEvent.setDescription(description);
        em.getTransaction().begin();

        em.merge(newEvent);

        em.persist(newEvent);

        em.getTransaction().commit();

        System.out.println("Événement ajouté avec succès.");
        //em.persist(newEvent);
        System.out.println(newEvent);

        return newEvent;
    }

    @Override
    @Transactional
    public void deleteEvent(int id){
        Event eventToDelete = getEventId(id);
        if (eventToDelete != null) {
            em.remove(eventToDelete);
        } else {
            System.out.println("L'événement avec l'ID " + id + " n'existe pas.");
        }
    }

    private Collection<Integer> getAllEventId(){
        return em.createQuery("SELECT event.idEvent FROM Event event", Integer.class).getResultList();
    }
    private boolean isEventExists(int id) {
        Collection<Integer> allEventIds = getAllEventId();
        System.out.println(allEventIds.contains(id));
        return allEventIds.contains(id);
    }


}
