package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import fr.pantheonsorbonne.ufr27.miage.service.DonationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

@Path("government")
public class DonationResource {
    @Inject
    DonationService donationService;

    @Path(("/allDonations"))
    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Donation> getAlert() {
        return this.donationService.getDonationService();
    }


    @Path("regionsCall/{regions}/RegionOfNeed/{RegionOfNeed}/description/{description}")
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void postAddEvent( @PathParam("regions") Collection<String> regions,@PathParam("RegionOfNeed") String RegionOfNeed,@PathParam("description") String description) {
        this.donationService.addDonationService(regions,RegionOfNeed,description);
    }

 /*
    @Path("deleteEvent/{alertId}")
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteEvent(@PathParam("alertId") int alertId) {
        this.alertService.deleteEventServiceId(alertId);
    }
*/
}