package ch.zli.m223.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
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

import ch.zli.m223.model.Role;
import ch.zli.m223.service.RoleService;

@Path("/role")
@Tag(name = "Role", description = "Handling of roles")
public class RoleController {

    @Inject
    RoleService roleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Index all Roles.", description = "Returns a list of all roles.")
    public List<Role> index() {
        return roleService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Creates a new role.", description = "Creates a new role and returns the newly added role.")
    public Role create(Role role) {
       return roleService.create(role);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Deletes a role", description = "Deletes a role and returns status code")
    public Response delete(Long id){
        return roleService.delete(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Update a role", description = "Updates a role and returns role")
    @Transactional
    public Role updatePut(Long id, Role role){
        return roleService.update(id, role);
    }
}
