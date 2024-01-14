package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Event;

import java.util.Collection;

public interface EventDAO {

    Collection<Event> getEvent();

    Event getEventId(int id);

    Event postEventRegion(int id, String region);

    void addEvent(Event event);
    void deleteEvent(int id);

    void sendAllEvent(Event event);
}
