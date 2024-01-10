package top.nextnet.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.PUT;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import jakarta.ws.rs.Path;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import top.nextnet.service.PurchaseConfirmationGateway;


@Path("/user")
@RegisterRestClient(configKey = "user-api")
public class UserResource {

    @Inject
    private PurchaseConfirmationGateway gateway;

    @Path("purchase/{idPurchase}/confirm")
    @PUT
    public void confirmPurchase(@PathParam int idPurchase)
    {
        gateway.confirmWeChatPurchase(idPurchase);
    }

}
