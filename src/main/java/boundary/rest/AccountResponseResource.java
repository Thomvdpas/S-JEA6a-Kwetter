package boundary.rest;

import domain.Account;
import service.AccountService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * @author Thom van de Pas on 9-3-2018
 */
@Path("accounts")
@Stateless
public class AccountResponseResource {

    @Inject
    private AccountService accountService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        GenericEntity entity = new GenericEntity<List<Account>>(accountService.findAll()) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAccount(@QueryParam("id") Long id) {
        Account account = accountService.findById(id);
        if (account == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(account).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAccount(Account account) {
        Account foundAccount = accountService.findById(account.getId());
        if (foundAccount == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        accountService.update(account);
        return Response.ok(foundAccount).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account) {
        if (account == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        accountService.create(account);
        URI id = URI.create(account.getUsername());
        return Response.created(id).build();
    }

    @DELETE
    @Path("{username}")
    public Response deleteAccount(String username) {
        accountService.delete(accountService.findByUsername(username));
        return Response.noContent().build();
    }

    public AccountResponseResource() {
    }
}
