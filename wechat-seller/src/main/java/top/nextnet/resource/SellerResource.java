package top.nextnet.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import jakarta.ws.rs.Path;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import top.nextnet.service.PurshasingGateway;


@Path("/seller")
@RegisterRestClient(configKey = "seller-api")
public class SellerResource {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.externalSellerUserName")
    String externalSellerUserName;

    @Inject
    private PurshasingGateway gateway;


    @Path("purchase/new/{weChatUser}/{amount}")
    @POST
    public void createPurchase(@PathParam String weChatUser, @PathParam double amount)
    {
        gateway.sendWeChatPurshasing(weChatUser, externalSellerUserName, amount);
    }

}
