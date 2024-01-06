package top.nextnet.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import jakarta.ws.rs.Path;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import top.nextnet.service.PurshasingGateway;


@Path("/seller")
@RegisterRestClient(configKey = "seller-api")
public class SellerResource {

    @Inject
    private PurshasingGateway gateway;

    @Path("purchase/new/{idES}/{idWC}/{amount}")
    @POST
    public void createPurchase(@PathParam int idES, @PathParam int idWC, @PathParam int amount)
    {
        gateway.sendWeChatPurshasing(idWC, idES, amount);
    }

}
