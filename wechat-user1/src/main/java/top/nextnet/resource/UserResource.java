package top.nextnet.resource;


import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import top.nextnet.camel.gateways.GivingGateway;



@Path("user")
@RegisterRestClient(configKey = "user-api")
public class UserResource {

    @Inject
    GivingGateway gateway;
/*
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.regionUser")
    String userRegion;
*/

    @Path("/give/{donationId}/{typeGive}/{quantity}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void giveDonation(@PathParam("donationId") int donationId,
                             @PathParam("typeGive") String typeGive,
                             @PathParam("quantity") int quantity) {
        this.gateway.sendGivingOrder(new Giving(donationId, "ile-de-france", typeGive, quantity));
    }

    @Path("/give")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void giveDonation() {
        this.gateway.sendGivingOrder(new Giving(1, "ile-de-france", "typeGive", 50));
    }

}