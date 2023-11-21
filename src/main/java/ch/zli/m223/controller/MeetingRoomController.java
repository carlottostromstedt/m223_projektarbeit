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

import ch.zli.m223.model.MeetingRoom;
import ch.zli.m223.service.MeetingRoomService;

@Path("/meeting_room")
@Tag(name = "MeetingRoom", description = "Handling of meeting rooms")
public class MeetingRoomController {

    @Inject
    MeetingRoomService meetingRoomService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator", "Member" })
    @Operation(summary = "Index all meeting rooms.", description = "Returns a list of all meeting rooms.")
    public List<MeetingRoom> index() {
        return meetingRoomService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator", "Member" })
    @Operation(summary = "Show a meeting room.", description = "Returns a single meeting rooms.")
    public MeetingRoom show(Long id) {
        return meetingRoomService.geMeetingRoom(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Creates a new meeting room.", description = "Creates a new meeting room and returns the newly added meeting room.")
    public MeetingRoom create(MeetingRoom role) {
       return meetingRoomService.create(role);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Deletes a meeting room", description = "Deletes a meeting room and returns status code")
    public Response delete(Long id){
        return meetingRoomService.delete(id);
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Update a meeting room", description = "Updates a meeting room and returns meeting room")
    @Transactional
    public MeetingRoom updatePut(Long id, MeetingRoom meetingRoom){
        return meetingRoomService.update(id, meetingRoom);
    }
}