package main.boundary.rest;

import io.swagger.annotations.Api;
import main.domain.Account;
import main.domain.Kweet;
import main.service.AccountService;
import main.service.KweetService;
import main.service.ProfileService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * @author Thom van de Pas on 9-3-2018
 */
@Path("kweets")
@Api
@Stateless
public class KweetResponseResource {

    /**
     * Injects the AccountService and KweetService.
     */
    @Inject
    private AccountService accountService;
    @Inject
    private ProfileService profileService;
    @Inject
    private KweetService kweetService;

    /**
     * Finds all the Kweets.
     *
     * @returns a List of all the Kweets.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        List<Kweet> allKweets = this.kweetService.findAll();
        return Response.ok(this.kweetService.multipleToJson(allKweets)).build();
    }

    /**
     * Get Kweet by its id.
     *
     * @param id
     * @returns the found Kweet in JSON or throws a WebApplicationException.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getKweetById(@PathParam("id") long id) {
        Kweet kweet = kweetService.findById(id);

        if (kweet == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Response.ok(kweet.toJson()).build();
    }

    @GET
    @Path("find/{message}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findByMessage(@PathParam("message") String message) {
        List<Kweet> kweets = kweetService.findByBodyText(message);
        return Response.ok(this.kweetService.multipleToJson(kweets)).build();

    }

//    /**
//     * Finds a List of Kweets based on a Account (Gets all Users Kweet)
//     *
//     * @param id
//     * @returns a List of Kweets.
//     */
//    @GET
//    @Path("{senderId}")
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response findKweetsBySender(@QueryParam("senderId") Long id) {
//        Profile profile = profileService.findById(id);
//        if (profile != null) {
//            GenericEntity entity = new GenericEntity<List<Kweet>>(kweetService.findBySender(profile)) {
//            };
//            return Response.ok(entity).build();
//        }
//        return null;
//    }

    /**
     * Updates a Kweet.
     *
     * @param kweet
     * @returns a Kweet in JSON format.
     */
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

    /**
     * Creates a new Kweet.
     *
     * @param kweet
     * @returns the new Kweet in JSON.
     */
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

    /**
     * Deletes a Kweet based on its id.
     *
     * @param id
     * @returns Status.NO_CONTENT
     */
    @DELETE
    @Path("{id}")
    public Response deleteKweet(@PathParam("id") Long id) {
        kweetService.delete(id);
        return Response.noContent().build();
    }

    /**
     * Finds all the Kweets of followers by a accountId.
     * @param id
     * @return the Response.
     */
    @GET
    @Path("findKweetsByAccountFollowings/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllFollowingsKweets(@PathParam("id") Long id) {
        if (id == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Account foundAccount = this.accountService.findById(id);
        if (foundAccount == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

//        return Response.ok(this.kweetService.findAllKweetsFromFollowers(foundAccount)).build();
        return null;
    }

    /**
     * Empty constructor.
     */
    public KweetResponseResource() {
    }
}
