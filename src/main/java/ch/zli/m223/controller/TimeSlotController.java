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

import ch.zli.m223.model.TimeSlot;
import ch.zli.m223.service.TimeSlotService;

@Path("/time_slot")
@Tag(name = "TimeSlot", description = "Handling of time slots")
public class TimeSlotController {

    @Inject
    TimeSlotService timeSlotService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Index all time slots.", description = "Returns a list of all time slots.")
    public List<TimeSlot> index() {
        return timeSlotService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Get a single time slot.", description = "Returns a single time slot.")
    public TimeSlot show(Long id) {
        return timeSlotService.getTimeSlot(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Creates a new time slot.", description = "Creates a new time slot and returns the newly added time slot.")
    public TimeSlot create(TimeSlot timeSlot) {
       return timeSlotService.create(timeSlot);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Deletes a time slot", description = "Deletes a time slot and returns status code")
    public Response delete(Long id){
        return timeSlotService.delete(id);
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Update a time slot", description = "Updates a time slot and returns time slot")
    @Transactional
    public TimeSlot updatePut(Long id, TimeSlot timeSlot){
        return timeSlotService.update(id, timeSlot);
    }
}
