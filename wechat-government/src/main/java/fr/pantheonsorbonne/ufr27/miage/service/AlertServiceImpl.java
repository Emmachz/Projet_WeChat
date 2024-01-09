package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.AlertGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.EventDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.exception.EventNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Event;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class AlertServiceImpl implements AlertService {

    @Inject
    EventDAO eventdao;

    @Inject
    AlertGateway alertGateway;

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Alert alert(Alert alert) throws EventNotFoundException {
        try {


        } catch (NonUniqueResultException | NoResultException e) {
            throw new EventNotFoundException(alert.getAlertId());
        }
        return alert;

    }

    public Collection<Event> getEventService(){
        return this.eventdao.getEvent();
    }
    public Event getEventServiceId(int id){
        return this.eventdao.getEventId(id);
    }

    public Event postEventService( int id, String region){
        return this.eventdao.postEventRegion(id, region);
    }

    @Transactional
    public void addEventService(Event event) {
        this.eventdao.addEvent(event);
        this.alertGateway.sendAlert(event);
    }

    @Transactional
    public void addEventService(int id, String category, String region, String date, String hour, String description, String level, String status){
        this.eventdao.addEvent(id, category, region, date, hour, description, level, status);
    }

    @Transactional
    public void deleteEventServiceId(int id){
        this.eventdao.deleteEvent(id);
    }



}
