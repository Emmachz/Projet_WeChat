package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Event;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
        Event attachedEvent = em.merge(event);
        em.persist(attachedEvent);
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

    @Override
    @Transactional
    public void sendAllEvent(Event event){
        Event newEvent = new Event(event.getCategory(), event.getRegion(), event.getDate(), event.getHour(), event.getDescription());
        Event attachedEvent = em.merge(event);
        em.persist(attachedEvent);
    }

}
