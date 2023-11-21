package ch.zli.m223.controller;

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

import ch.zli.m223.model.Nocco;
import ch.zli.m223.service.NoccoService;

@Path("/nocco")
@Tag(name = "Nocco", description = "Handling of noccos")
public class NoccoController {

    @Inject
    NoccoService noccoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator", "Member" })
    @Operation(summary = "Index all noccos.", description = "Returns a list of all noccos.")
    public List<Nocco> index() {
        return noccoService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator", "Member" })
    @Operation(summary = "Show a Nocco.", description = "Returns a single nocco.")
    public Nocco show(Long id) {
        return noccoService.getNocco(id);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Creates a new nocco.", description = "Creates a new nocco and returns the newly added nocco.")
    public Nocco create(Nocco nocco) {
       return noccoService.create(nocco);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Deletes a nocco", description = "Deletes a nocco and returns status code")
    public Response delete(Long id){
        return noccoService.delete(id);
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Update a nocco", description = "Updates a nocco and returns nocco")
    @Transactional
    public Nocco updatePut(Long id, Nocco nocco){
        return noccoService.update(id, nocco);
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Update a nocco partially", description = "Updates a nocco partially and returns nocco")
    @Transactional
    public Nocco updatePatch(Long id, Nocco nocco){
        return noccoService.partialUpdate(id, nocco);
    }
}
