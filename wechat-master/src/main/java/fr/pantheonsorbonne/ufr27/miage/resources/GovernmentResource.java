
package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.service.DonationService;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
@Path("government")
public class GovernmentResource {

    @Inject
    donationService DonationService;

    @Path(("post/{donationId}"))
    @POST
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String[] requestDonation(Donation d) {
        return donationService.requestDonation(donationId);
    }

    @Path("delete/{donationId}")
    @DELETE
    public void cancelDonation(@PathParam("donationId") int donationId) {
        donationService.cancelDonation(donationId);
    }
}
 