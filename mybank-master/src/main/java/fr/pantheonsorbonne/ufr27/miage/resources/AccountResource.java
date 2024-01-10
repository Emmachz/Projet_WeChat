package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.services.AccountService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("account")
@RegisterRestClient(configKey = "mybank-api")
public class AccountResource {

    @Inject
    AccountService accountService;

    @Path("{id}/credit/{amount}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void creditAccount(@PathParam("id") int accountId, @PathParam("amount") int creditedAmount) {
        accountService.creditMoney(accountId, creditedAmount);
    }
}

