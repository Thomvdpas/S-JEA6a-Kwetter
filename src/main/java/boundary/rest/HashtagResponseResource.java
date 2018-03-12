package boundary.rest;

import domain.Hashtag;
import domain.Heart;
import domain.Kweet;
import jdk.nashorn.internal.objects.annotations.Getter;
import service.HashtagService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Thom van de Pas on 9-3-2018
 */
@Path("hashtags")
@Stateless
public class HashtagResponseResource {

    @Inject
    private HashtagService hashtagService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        GenericEntity entity = new GenericEntity<List<Hashtag>>(hashtagService.findAll()) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHashtag(@QueryParam("id") Long id) {
        Hashtag hashtag = hashtagService.findById(id);
        if (hashtag == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(hashtag).build();
    }

    @GET
    @Path("{bodyText}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getByBodyText(@QueryParam("bodyText") String bodyText) {
        if (bodyText != null) {
            GenericEntity entity = new GenericEntity<List<Hashtag>>(hashtagService.findByBodyText(bodyText)) {
            };

            return Response.ok(entity).build();
        }
        return null;
    }
}
