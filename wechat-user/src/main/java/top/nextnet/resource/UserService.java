package top.nextnet.resource;


import fr.pantheonsorbonne.ufr27.miage.dto.Donation;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import java.util.Collection;


@Path("/user")
@RegisterRestClient(configKey = "user-api")
public interface UserService {



    @Path("{userId}/donations")
    @GET
    Collection<Donation> getDonations(@PathParam String userRegion);
}
