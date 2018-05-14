package main.boundary.rest;

import io.swagger.annotations.Api;
import main.domain.Account;
import main.service.AccountService;

import javax.crypto.KeyGenerator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * @author Thom van de Pas on 9-3-2018
 */
@Path("accounts")
@Api
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
        List<Account> foundAccounts = this.accountService.findAll();
        return Response.ok(this.accountService.multipleToJson(foundAccounts)).build();
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
    public JsonObject findById(@PathParam("id") Long id) {
        Account account = accountService.findById(id);
        if (account == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        UriBuilder uriBuilder = UriBuilder.fromResource(AccountResponseResource.class)
                .path(AccountResponseResource.class, "findById");
        Link link = Link.fromUri(uriBuilder.build(id)).rel("self").build();

        return Json.createObjectBuilder()
                .add("username", account.getUsername())
                .add("emailaddress", account.getEmailaddress())
                .add("profile", Json.createObjectBuilder()
                        .add("firstName", account.getProfile().getFirstName())
                        .add("lastName", account.getProfile().getLastName()).build())
                .add(link.getRel(), link.getUri().getPath())
                .build();
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
