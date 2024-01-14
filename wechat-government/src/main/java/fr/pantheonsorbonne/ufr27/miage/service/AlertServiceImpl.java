package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.AlertGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.EventDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Event;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.Collection;

@ApplicationScoped
public class AlertServiceImpl implements AlertService {

    @Inject
    EventDAO eventdao;

    @Inject
    AlertGateway alertGateway;

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
    public String addEventService(Event event) {
        if (isValidRegion(event)) {
            this.eventdao.addEvent(event);
            this.alertGateway.sendAlert(event);
            return "Event bien envoyé";
        } else {
            return "Erreur dans l'entrée de la région";
        }
    }

    @Transactional
    public void deleteEventServiceId(int id){
        this.eventdao.deleteEvent(id);
    }

    private boolean isValidRegion(Event event) {
        String[] validRegions = {"auvergne-rhone-alpes", "bourgogne-franche-comte", "bretagne", "corse",
                "centre-val-de-loire", "grand-est", "hauts-de-france", "ile-de-france", "nouvelle-aquitaine",
                "normandie", "occitanie", "provence-alpes-cote-dazur", "pays-de-la-loire", "all"};
        return Arrays.asList(validRegions).contains(event.getRegion());
    }

}
