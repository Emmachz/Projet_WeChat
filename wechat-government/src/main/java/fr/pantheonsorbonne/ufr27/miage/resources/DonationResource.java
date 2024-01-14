package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import fr.pantheonsorbonne.ufr27.miage.service.DonationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;
import java.util.List;

@Path("government")
public class DonationResource {
    @Inject
    DonationService donationService;

    @Path(("/allDonations"))
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Donation> getAlert() {
        return this.donationService.getDonationService();
    }


    @Path("/createDonation/RegionOfNeed/{RegionOfNeed}/description/{description}/moneySupport/{moneySupport}/timeSupport/{timeSupport}/clotheSupport/{clotheSupport}")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void postAddEvent( @PathParam("RegionOfNeed") String RegionOfNeed, @PathParam("description") String description, @PathParam("moneySupport") double moneySupport, @PathParam("timeSupport") double timeSupport, @PathParam("clotheSupport") double clotheSupport) {
        this.donationService.addDonationService(RegionOfNeed,description, moneySupport, timeSupport, clotheSupport);
    }


    @Path("deleteDonation/{donationId}")
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteEvent(@PathParam("donationId") int donationId) {
        this.donationService.deleteDonationService(donationId);
    }

}