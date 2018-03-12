package boundary.rest;

import domain.Account;
import domain.Kweet;
import service.AccountService;
import service.KweetService;

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
    @Path("{senderId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findKweetsBySender(@QueryParam("senderId") Long id) {
        Account account = accountService.findById(id);
        if (account != null) {
            GenericEntity entity = new GenericEntity<List<Kweet>>(kweetService.findBySender(account)) {
            };
            return Response.ok(entity).build();
        }
        return null;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editKweet(Kweet kweet) {
        Kweet foundKweet = kweetService.findById(kweet.getId());
        if (foundKweet == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        kweetService.update(kweet);
        return Response.ok(foundKweet).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createKweet(Kweet kweet) {
        if (kweet == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        kweetService.create(kweet);
        URI id = URI.create(kweet.getMessageBody());
        return Response.created(id).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        kweetService.delete(id);
        return Response.noContent().build();
    }
}
