package main.boundary.rest;

import main.domain.Hashtag;
import io.swagger.annotations.Api;
import main.service.HashtagService;

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
@Path("hashtags")
@Api
@Stateless
public class HashtagResponseResource {

    /**
     * Injects the HashtagService.
     */
    @Inject
    private HashtagService hashtagService;

    /**
     * Finds all the Hashtags
     *
     * @returns all the Hashtag in JSON format.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        GenericEntity entity = new GenericEntity<List<Hashtag>>(hashtagService.findAll()) {
        };
        return Response.ok(entity).build();
    }

    /**
     * Finds a Hashtag based on its id.
     * @param bodyText
     * @returns the found Hashtag or null.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHashtag(@PathParam("id") Long id) {
        Hashtag hashtag = hashtagService.findById(id);
        if (hashtag == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(hashtag.toJson()).build();
    }

    @GET
    @Path("find/{subject}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findBySubject(@PathParam("subject") String subject) {
        Hashtag hashtag = hashtagService.findByBodyText(subject);
        if (hashtag != null) {
            return Response.ok(hashtag.toJson()).header("Access-Control-Allow-Origin", "*").build();
        }
        return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
    }

    /**
     * Updates a Hashtag.
     *
     * @param hashtag
     * @returns the updated Hashtag in JSON format.
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editHashtag(Hashtag hashtag) {
        Hashtag foundHashtag = hashtagService.findById(hashtag.getId());
        if (foundHashtag == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        hashtagService.update(hashtag);
        return Response.ok(foundHashtag).build();
    }

    /**
     * Creates a new Hashtag.
     *
     * @param hashtag
     * @returns the new Hashtag in JSON format.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createHashtag(Hashtag hashtag) {
        if (hashtag == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        hashtagService.create(hashtag);
        URI id = URI.create(hashtag.getBodyText());
        return Response.noContent().build();
    }

    /**
     * Deletes a Hashtag based on its id.
     *
     * @param id
     * @returns a Status.NO_CONTENT
     */
    @DELETE
    @Path("{id}")
    public Response deleteHashtag(@PathParam("id") Long id) {
        hashtagService.delete(id);
        return Response.noContent().build();
    }
}
