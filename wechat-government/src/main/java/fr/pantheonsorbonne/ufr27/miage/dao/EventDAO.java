package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Event;

import java.util.Collection;

public interface EventDAO {

    Collection<Event> getEvent();

    Event getEventId(int id);

    Event postEventRegion(int id, String region);

    void addEvent(Event event);

    Event addEvent(int id, String category, String region, String date, String hour, String description, String level, String Status);

    void deleteEvent(int id);
}