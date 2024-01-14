package top.nextnet.resource;

import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import top.nextnet.camel.gateways.GivingGateway;

import java.util.Collection;


@Path("/user")
@RegisterRestClient(configKey = "user-api")
public class UserResource {
    @Inject
    GivingGateway gateway;

    @Path("/give/{donationId}/{typeGive}/{quantity}")
    @GET
    //@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void giveDonation(@jakarta.ws.rs.PathParam("donationId") int donationId,
                             @jakarta.ws.rs.PathParam("typeGive") String typeGive,
                             @jakarta.ws.rs.PathParam("quantity") int quantity) {
        this.gateway.sendGivingOrder(new Giving(donationId, "ile-de-france", typeGive, quantity));
    }
/*
    @Path("/getAllAlert")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Alert> getAlert() {

        return this.alertService.getEventService();
    }
    */


}
