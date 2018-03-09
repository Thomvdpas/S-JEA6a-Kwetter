package boundary.rest;

import domain.Account;
import domain.Kweet;
import service.KweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    private KweetService kweetService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        GenericEntity entity = new GenericEntity<List<Kweet>>(kweetService.findAll()) {
        };
        return Response.ok(entity).build();
    }
}
