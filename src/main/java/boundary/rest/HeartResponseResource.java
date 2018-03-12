package boundary.rest;

import domain.Hashtag;
import domain.Heart;
import domain.Kweet;
import service.HeartService;
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
 * @author Thom van de Pas on 10-3-2018
 */
@Path("/hearts")
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
}
