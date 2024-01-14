package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Event;

import java.util.Collection;

public interface AlertService {

    Collection<Event> getEventService();

    Event getEventServiceId(int id);

    Event postEventService( int id, String region);

    String addEventService(Event event);

    void deleteEventServiceId(int id);

}
