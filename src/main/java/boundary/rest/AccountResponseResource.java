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

    /**
     * Injects the AccountService in the AccountResponseResource.
     */
    @Inject
    private AccountService accountService;

    /**
     * Gets all the Accounts
     *
     * @returns all the Accounts in JSON format.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        GenericEntity entity = new GenericEntity<List<Account>>(accountService.findAll()) {
        };
        return Response.ok(entity).build();
    }

    /**
     * Gets an account based on its id
     *
     * @param id
     * @returns the found Account or a NOT_FOUND status if account == null;
     */
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

    /**
     * Updates an Account.
     *
     * @param account
     * @returns Status.NOT_FOUND if there is no Account found.
     */
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

    /**
     * Creates a new Account
     *
     * @param account
     * @returns the created Account.
     */
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

    /**
     * Deletes an Account based on it's username.
     *
     * @param username
     * @returns Status.NO_CONTENT
     */
    @DELETE
    @Path("{username}")
    public Response deleteAccount(String username) {
        accountService.delete(accountService.findByUsername(username));
        return Response.noContent().build();
    }

    /**
     * Empty constructor.
     */
    public AccountResponseResource() {
    }
}
