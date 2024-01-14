package top.nextnet.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import top.nextnet.service.PurchaseConfirmationGateway;

@Path("/purchase")
@RegisterRestClient(configKey = "user-api")
public class PurchaseResource {

    @Inject
    private PurchaseConfirmationGateway confirmationGateway;

    @Path("{idPurchase}/confirm")
    @PUT
    public void confirmPurchase(@PathParam int idPurchase) {
        confirmationGateway.confirmWeChatPurchase(idPurchase);
    }
}
