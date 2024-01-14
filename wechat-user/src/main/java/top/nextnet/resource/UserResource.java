package top.nextnet.resource;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Path;
import top.nextnet.camel.gateways.UserGateway;


@Path("/user")
@RegisterRestClient(configKey = "user-api")
public class UserResource {

    @Inject
    private UserGateway gateway;

    @Path("{loginUser}/versement/{loginUserDest}/amount/{value}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String versementUserAUser(@jakarta.ws.rs.PathParam("loginUser") String loginUser,
                                     @jakarta.ws.rs.PathParam("loginUserDest") String loginUserDest,
                                     @jakarta.ws.rs.PathParam("value") double value) {

        return gateway.sendTransfertInfos(new TransfertArgent(loginUser, loginUserDest, value));

    }

}
