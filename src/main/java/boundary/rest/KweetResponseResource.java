package boundary.rest;

import domain.Account;
import domain.Heart;
import domain.Kweet;
import service.AccountService;
import service.KweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Thom van de Pas on 9-3-2018
 */
@Path("kweets")
@Stateless
public class KweetResponseResource {

    @Inject
    private AccountService accountService;
    @Inject
    private KweetService kweetService;


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        GenericEntity entity = new GenericEntity<List<Kweet>>(kweetService.findAll()) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAllKweetsByUserId(@QueryParam("senderId") Long id) {
        Account account = accountService.findById(id);
        if (account != null) {
            GenericEntity entity = new GenericEntity<List<Kweet>>(kweetService.findBySender(account)) {
            };
            return Response.ok(entity).build();
        }
        return null;
    }
}
