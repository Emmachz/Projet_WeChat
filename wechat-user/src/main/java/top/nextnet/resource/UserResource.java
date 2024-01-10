package top.nextnet.resource;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import jakarta.ws.rs.Path;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import top.nextnet.service.PurchaseConfirmationGateway;
import top.nextnet.service.UserGateway;


@Path("/user")
@RegisterRestClient(configKey = "user-api")
public class UserResource {

    @Inject
    private UserGateway gateway;

    @Inject
    private PurchaseConfirmationGateway confirmationGateway;

    @Path("purchase/{idPurchase}/confirm")
    @PUT
    public void confirmPurchase(@PathParam int idPurchase)
    {
        confirmationGateway.confirmWeChatPurchase(idPurchase);
    }

    @Path("{loginUser}/versement/{loginUserDest}/amount/{value}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void versementUserAUser(@jakarta.ws.rs.PathParam("loginUser") String loginUser,
                                   @jakarta.ws.rs.PathParam("loginUserDest") String loginUserDest,
                                   @jakarta.ws.rs.PathParam("value") double value) {

        gateway.sendTransfertInfos(new TransfertArgent(loginUser, loginUserDest, value));
    }

}
