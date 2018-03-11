package boundary.rest;

import dao.AccountDao;
import domain.Account;
import domain.Hashtag;
import service.AccountService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
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
    @Path("id")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAccount(@QueryParam("id") Long id) {
        Account account = accountService.findById(id);
        if (account == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(account).build();
    }
}
