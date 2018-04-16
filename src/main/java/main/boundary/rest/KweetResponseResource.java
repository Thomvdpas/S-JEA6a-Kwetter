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
        return Response.ok(this.kweetService.multipleToJson(allKweets)).header("Access-Control-Allow-Origin", "*").build();
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

    /**
     * Finds a List of Kweets based on an Account (Gets all Users Kweet)
     *
     * @param id
     * @returns a List of Kweets.
     */
    @GET
    @Path("account/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findKweetsBySender(@PathParam("id") Long id) {
        Account account = this.accountService.findById(id);
        if (account != null) {
            List<Kweet> myKweets = this.kweetService.findBySender(account);

            return Response.ok(this.kweetService.multipleToJson(myKweets)).header("Access-Control-Allow-Origin", "*").build();
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

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
        return Response.created(id).header("Access-Control-Allow-Origin", "*").build();
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
     *
     * @param id
     * @return the Response.
     */
    @GET
    @Path("timelineKweets/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllFollowingsKweets(@PathParam("id") Long id) {
        if (id == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Account foundAccount = this.accountService.findById(id);
        if (foundAccount == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        List<Kweet> allFoundKweets = this.kweetService.findAllKweetsFromFollowers(foundAccount);

        return Response.ok(this.kweetService.multipleToJson(allFoundKweets)).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("lastTenKweets/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findMyTenLastKweets(@PathParam("id") Long id) {
        if (id == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Account foundAccount = this.accountService.findById(id);
        if (foundAccount == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        List<Kweet> myTenLastKweets = this.kweetService.findMyLastTenKweets(foundAccount);

        return Response.ok(this.kweetService.multipleToJson(myTenLastKweets)).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("last/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastKweet(@PathParam("id") Long id) {
        if (id != null) {
            Account foundAccount = this.accountService.findById(id);
            if (foundAccount == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }

            Kweet foundKweet = this.kweetService.findLast(foundAccount);
            return Response.ok(foundKweet.toJson()).build();
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @GET
    @Path("mentions/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findMentions(@PathParam("id") Long id) {
        if (id == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Account foundAccount = this.accountService.findById(id);
        if (foundAccount == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        List<Kweet> myMentions = this.kweetService.findByMention(foundAccount);

        return Response.ok(this.kweetService.multipleToJson(myMentions)).header("Access-Control-Allow-Origin", "*").build();
    }

    /**
     * Empty constructor.
     */
    public KweetResponseResource() {
    }
}
