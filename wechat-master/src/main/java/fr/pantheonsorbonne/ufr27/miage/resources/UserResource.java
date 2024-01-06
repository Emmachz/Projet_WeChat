
package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.service.VenueService;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("user")
public class UserResource {

    @Inject
    donationService service;

    @Path("{/see/donations")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Donation> getDonations() {
        return service.getDemandDonations();
    }
/*
    @Path("{see/{idDonation}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Donation> getDonations(@PathParam("idVendor") int idVendor) {
        return service.getDemandDonations(idVendor);
    }

    @Path(("{userId}/give/{giveName}/{donationId}"))
    @POST
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String[] giveDonation(Ticket t) {
        return donationService.giveDonation(donationId);
    }
*/

}
