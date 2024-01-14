
package top.nextnet.resource;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import top.nextnet.camel.gateways.GivingGateway;


@Path("/user")
@RegisterRestClient(configKey = "user-api")
public class UserResource {

    @Inject
    GivingGateway gateway;

    @Inject
    private UserGateway usergateway;

    @Path("/give/{userLogin}/{helpId}/{typeGive}/{quantity}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void giveDonation(@PathParam("helpId") int helpId,
                             @PathParam("typeGive") String typeGive,
                             @PathParam("userLogin") String userLogin,
                             @PathParam("quantity") double quantity) {
        this.gateway.sendGivingOrder(new Giving(helpId, userLogin, typeGive, quantity));
    }

    @Path("{loginUser}/versement/{loginUserDest}/amount/{value}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean versementUserAUser(@jakarta.ws.rs.PathParam("loginUser") String loginUser,
                                      @jakarta.ws.rs.PathParam("loginUserDest") String loginUserDest,
                                      @jakarta.ws.rs.PathParam("value") double value) {

        return usergateway.sendTransfertInfos(new TransfertArgent(loginUser, loginUserDest, value));

    }
}