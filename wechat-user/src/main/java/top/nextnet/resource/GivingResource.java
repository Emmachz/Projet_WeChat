package top.nextnet.resource;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import top.nextnet.camel.gateways.GivingGateway;
@Path("/give")
@RegisterRestClient(configKey = "user-api")
public class GivingResource {

    @Inject
    GivingGateway gateway;

    @Path("{userLogin}/{helpId}/{typeGive}/{quantity}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void giveDonation(@PathParam("helpId") int helpId,
                             @PathParam("typeGive") String typeGive,
                             @PathParam("userLogin") String userLogin,
                             @PathParam("quantity") double quantity) {
        this.gateway.sendGivingOrder(new Giving(helpId, userLogin, typeGive, quantity));
    }
}
