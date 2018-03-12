package boundary.rest;

import domain.Heart;
import domain.Kweet;
import service.HeartService;
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
 * @author Thom van de Pas on 10-3-2018
 */
@Path("hearts")
@Stateless
public class HeartResponseResource {

    @Inject
    private HeartService heartService;
    @Inject
    private KweetService kweetService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        GenericEntity entity = new GenericEntity<List<Heart>>(heartService.findAll()) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findByKweet(@QueryParam("kweetId") Long id) {
        Kweet kweet = kweetService.findById(id);
        if (kweet != null) {
            GenericEntity entity = new GenericEntity<List<Heart>>(heartService.findByKweet(kweet)) {
            };

            return Response.ok(entity).build();
        }
        return null;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editHeart(Heart heart) {
        Heart foundHeart = heartService.findById(heart.getId());
        if (foundHeart == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        heartService.update(heart);
        return Response.ok(foundHeart).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createHeart(Heart heart) {
        if (heart == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        heartService.create(heart);
        URI id = URI.create(heart.toString());
        return Response.created(id).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        heartService.delete(id);
        return Response.noContent().build();
    }
}
