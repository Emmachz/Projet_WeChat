package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.service.OperationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("account")
@RegisterRestClient(configKey = "mybank-api")
public class AccountResource {

    @Inject
    OperationService service;

    @Path("{bankNumber}/credit/{amount}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void creditAccount(@PathParam("bankNumber") String bankNumber, @PathParam("amount") double creditedAmount) {
        service.creditMoney(bankNumber, creditedAmount);
    }
}

