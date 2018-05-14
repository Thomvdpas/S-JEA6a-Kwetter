package main.boundary.rest;

import main.domain.Account;
import main.domain.UserGroup;
import main.service.AccountService;
import main.service.JWTStoreService;
import main.service.UserGroupService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
/**
 * @author Thom van de Pas on 23-4-2018
 */
@Stateless
@Path("/auth")
public class AuthResponseResource {
    @Inject
    JWTStoreService jwtStoreService;

    @Inject
    AccountService accountService;
    @Inject
    private UserGroupService userGroupService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(JsonObject credential) {
        String username = credential.getString("username");
        String password = credential.getString("password");

        Account foundAccount = accountService.findByCredentials(username, password);

        if(foundAccount == null) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }

        List<UserGroup> userGroups = this.userGroupService.findGroupsForAccount(foundAccount);
        List<String> groups = new ArrayList<>();

        userGroups.forEach(accountGroup -> groups.add(accountGroup.getGroupName()));

        String token = this.jwtStoreService.generateToken(username, groups);

        return Response.ok(token).header(AUTHORIZATION, "Bearer " + token).build();
    }
}
