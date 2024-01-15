package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.model.Event;
import fr.pantheonsorbonne.ufr27.miage.service.AlertService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

@Path("alert")
public class EventResource {
    @Inject
    AlertService alertService;

    @Path(("/allEvent"))
    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Event> getAlert() {
        return this.alertService.getEventService();
    }

    @Path("/{alertId}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Event getAlertId(@PathParam("alertId") int alertId) {
        return this.alertService.getEventServiceId(alertId);
    }

    @Path("/{alertId}/newRegion/{alertRegion}")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Event postAlertRegion(@PathParam("alertId") int alertId, @PathParam("alertRegion") String alertRegion) {
        return this.alertService.postEventService(alertId, alertRegion);
    }

    @Path("category/{category}/region/{region}/date/{date}/hour/{hour}/description/{description}")
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void postAddEvent( @PathParam("category") String category,@PathParam("region") String region,@PathParam("date") String date,@PathParam("hour") String hour, @PathParam("description") String description) {
        Event newEvent = new Event();
        newEvent.setCategory(category);
        newEvent.setRegion(region);
        newEvent.setDate(date);
        newEvent.setHour(hour);
        newEvent.setDescription(description);
        this.alertService.addEventService(newEvent);
    }

    @Path(("/addEvent"))
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String addEvent(Event event) {
        return this.alertService.addEventService(event);
    }

    @Path("deleteEvent/{alertId}")
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteEvent(@PathParam("alertId") int alertId) {
        this.alertService.deleteEventServiceId(alertId);
    }
}
