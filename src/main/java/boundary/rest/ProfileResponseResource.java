package boundary.rest;

import domain.Profile;
import service.ProfileService;

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
@Path("profiles")
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
        return Response.ok(entity).build();
    }

    /**
     * Gets a Profile based on its id.
     * @param username
     * @returns the Profile in JSON format.
     */
//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response findById(@QueryParam("senderId") Long id) {
//        Profile profile = profileService.findById(id);
//        if (profile == null) {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
//        }
//        return Response.ok(profile).build();
//    }

    /**
     * Finds a Profile based on its Username.
     *
     * @param username
     * @returns a Profile in JSON format or Status.NOT_FOUND.
     */
    @GET
    @Path("{username}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findByUsername(@QueryParam("username") String username) {
        Profile profile = profileService.findByUsername(username);
        if (profile == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(profile).build();
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