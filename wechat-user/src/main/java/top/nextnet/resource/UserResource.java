package top.nextnet.resource;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import org.jboss.resteasy.annotations.jaxrs.PathParam;
import top.nextnet.camel.gateways.GivingGateway;
import top.nextnet.service.PurchaseConfirmationGateway;


@Path("/user")
@RegisterRestClient(configKey = "user-api")
public class UserResource {

    @Inject
    private PurchaseConfirmationGateway egateway;

    @Path("purchase/{idPurchase}/confirm")
    @PUT
    public void confirmPurchase(@PathParam int idPurchase)
    {
        egateway.confirmWeChatPurchase(idPurchase);
    }

    @Inject
    GivingGateway gateway;
/*
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.regionUser")
    String userRegion;


    @Path("/give/{donationId}/{typeGive}/{quantity}")
    @GET
    //@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void giveDonation(@jakarta.ws.rs.PathParam("donationId") int donationId,
                             @jakarta.ws.rs.PathParam("typeGive") String typeGive,
                             @jakarta.ws.rs.PathParam("quantity") int quantity) {
        this.gateway.sendGivingOrder(new Giving(donationId, "ile-de-france", typeGive, quantity));
    }
*/
    @Path("/give")
    @GET
    //@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void giveDonation() {
        this.gateway.sendGivingOrder(new Giving(1,1, 1, "money", 50));
    }
}
