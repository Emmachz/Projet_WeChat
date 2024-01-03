package top.nextnet.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import top.nextnet.Model.UserInscript;
import top.nextnet.service.UserGateway;

@Path("/user")
public class UserResource {

    @Inject
    protected UserGateway service;

    @Path("{nomUser}/{loginUser}/{regionUser}/{numeroBank}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void getUserInfos(@PathParam("nomUser") String nomUser,
                                     @PathParam("loginUser") String loginUser,
                                     @PathParam("regionUser") String regionUser,
                                     @PathParam("numeroBank") int numeroBank) {

         service.sendUserInfos(new UserInscript(nomUser, loginUser, regionUser, numeroBank));
    }

    @Path("{nomUser}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String test(@PathParam("nomUser") String nomUser) {
        return "hello";
    }


}
