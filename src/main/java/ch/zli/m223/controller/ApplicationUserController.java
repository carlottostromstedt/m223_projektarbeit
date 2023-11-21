package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Credential;
import ch.zli.m223.service.ApplicationUserService;
import ch.zli.m223.service.SessionService;

@Path("/user")
@Tag(name = "ApplicationUser", description = "Handling of User")
public class ApplicationUserController {

    @Inject
    ApplicationUserService applicationUserService;

    @Inject
    SessionService sessionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Index all Users.", description = "Returns a list of all users.")
    public List<ApplicationUser> index() {
        return applicationUserService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    @Path("/register")
    @Operation(summary = "Creates a new user.", description = "Creates a new user and returns the newly added user.")
    public ApplicationUser register(ApplicationUser user) {
       return applicationUserService.create(user);
    }

    @POST
    @Path("/login")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Logs the user in.", description = "Logs the user in and returns a status code.")
    public Response login(Credential credential) {
        return sessionService.authenticate(credential);
    }
 
    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Deletes a user", description = "Deletes a user and returns status code")
    public Response delete(Long id){
        return applicationUserService.delete(id);
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({ "Administrator", "Member" })
    @Operation(summary = "Update a user", description = "Updates a user and returns user")
    @Transactional
    public ApplicationUser updatePut(Long id, ApplicationUser user){
        return applicationUserService.update(id, user);
    }
}
