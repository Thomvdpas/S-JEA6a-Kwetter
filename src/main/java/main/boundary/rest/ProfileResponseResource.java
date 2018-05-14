package main.boundary.rest;

import main.domain.Profile;
import io.swagger.annotations.Api;
import main.service.ProfileService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

/**
 * @author Thom van de Pas on 10-3-2018
 */
@Path("profiles")
@Api
@Stateless
public class ProfileResponseResource {

    /**
     * Injects the ProfileService
     */
    @Inject
    private ProfileService profileService;

    /**
     * Finds all the Profiles
     *
     * @returns a List of Profiles in JSON format.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        GenericEntity entity = new GenericEntity<List<Profile>>(profileService.findAll()) {
        };
        return Response.ok(entity).header("Access-Control-Allow-Origin", "*").build();
    }

    /**
     * Gets a Profile based on its id.
     * @param id
     * @returns the Profile in JSON format.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public JsonObject findById(@PathParam("id") Long id) {
        Profile profile = profileService.findById(id);
        if (profile == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        UriBuilder uriBuilder = UriBuilder.fromResource(ProfileResponseResource.class)
                .path(ProfileResponseResource.class, "findById");
        Link link = Link.fromUri(uriBuilder.build(id)).rel("self").build();

        return Json.createObjectBuilder()
                .add("id", profile.getId())
                .add("email", profile.getAccount().getEmailaddress())
                .add("username", profile.getAccount().getUsername())
                .add("firstName", profile.getFirstName())
                .add("lastName", profile.getLastName())
                .add("biography", profile.getBiography())
                .add("location", profile.getLocation())
                .add("dateOfBirth", profile.getDateOfBirth())
                .add("amountFollowers", profile.getFollowers().size())
                .add("amountFollowing", profile.getFollowings().size())
                .add("avatarPath", profile.getAvatarPath())
                .add(link.getRel(), link.getUri().getPath())
                .build();
    }

    /**
     * Finds a Profile based on its Username.
     *
     * @param username
     * @returns a Profile in JSON format or Status.NOT_FOUND.
     */
    @GET
    @Path("find/{username}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findByUsername(@PathParam("username") String username) {
        Profile profile = profileService.findByUsername(username);
        if (profile == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(profile.toJson()).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("find/followers/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowers(@PathParam("username") String username) {
        Profile foundProfile = profileService.findByUsername(username);
        if (foundProfile != null) {
            List<Profile> followers = foundProfile.getFollowers();
            return Response.ok(profileService.convertAllToJson(followers)).header("Access-Control-Allow-Origin", "*").build();
        }
        return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("find/following/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowing(@PathParam("username") String username) {
        Profile foundProfile = profileService.findByUsername(username);
        if (foundProfile != null) {
            List<Profile> followers = foundProfile.getFollowings();
            return Response.ok(profileService.convertAllToJson(followers)).header("Access-Control-Allow-Origin", "*").build();
        }
        return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
    }

    /**
     * Updates a Profile
     *
     * @param profile
     * @returns the updated Profile in JSON format.
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editProfile(Profile profile) {
        Profile foundProfile = profileService.findById(profile.getId());
        if (foundProfile == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        profileService.update(profile);
        return Response.ok(foundProfile).build();
    }

    /**
     * Creates a new Profile
     *
     * @param profile
     * @returns the created Profile in JSON format.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProfile(Profile profile) {
        if (profile == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        profileService.create(profile);
        URI id = URI.create(profile.getFullName());
        return Response.created(id).build();
    }

    /**
     * Deletes a Profile by its username.
     *
     * @param username
     * @returns Status.NO_CONTENT if succeeded.
     */
    @DELETE
    @Path("{username}")
    public Response deleteProfile(@PathParam("username") String username) {
        profileService.delete(profileService.findByUsername(username));
        return Response.noContent().build();
    }

    /**
     * Empty constructor
     */
    public ProfileResponseResource() {
    }
}