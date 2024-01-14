package top.nextnet.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import top.nextnet.service.PurchaseConfirmationGateway;

@Path("/purchase")
@RegisterRestClient(configKey = "user-api")
public class PurchaseResource {

    @Inject
    private PurchaseConfirmationGateway confirmationGateway;

    @Path("/{idPurchase}/confirm")
    @PUT
    public void confirmPurchase(@PathParam("idPurchase") Long idPurchase)
    {
        confirmationGateway.confirmWeChatPurchase(idPurchase);
    }
}
