package ch.zli.m223.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.h2.util.IOUtils;
import org.jboss.resteasy.reactive.RestHeader;
import org.eclipse.microprofile.jwt.JsonWebToken;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Role;
import ch.zli.m223.service.ApplicationUserService;
import ch.zli.m223.service.BookingService;
import io.netty.handler.codec.http.HttpHeaders;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;

@Path("/booking")
@Tag(name = "Booking", description = "Handling of bookings")
public class BookingController {

    @Inject
    BookingService bookingService;

    @Inject
    ApplicationUserService applicationUserService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Member", "Administrator" })
    @Operation(summary = "Index all bookings.", description = "Returns a list of all bookings.")
    public List<Booking> getMemberBookings(@Context SecurityContext ctx) {
        String jwtEmail = jwt.getClaim("email");

        // TODO: In service schieben
        ApplicationUser user = applicationUserService.getApplicationUserByMail(jwtEmail);

        Role userRole = user.getRole();
        String userType = userRole.getType();

        if (userType.equals("Member")){
            return bookingService.findMemberBookings(jwtEmail);
        } else if(userType.equals("Administrator")) {
            return bookingService.findAll();
        } else {
            return null;
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator", "Member" })
    @Operation(summary = "Show a booking.", description = "Returns a single booking.")
    public Booking show(Long id) {
        return bookingService.getBooking(id);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "Administrator", "Member" })
    @Operation(summary = "Creates a new booking.", description = "Creates a new booking and returns the newly added booking.")
    public Booking create(Booking booking) {
       return bookingService.create(booking);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}/accept")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Accept a booking.", description = "Accepts a booking and returns status code.")
    public Response accept(Long id) {
       return bookingService.accept(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}/decline")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Decline a booking.", description = "Declines a Booking and returns status code.")
    public Response decline(Long id) {
       return bookingService.decline(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}/cancel")
    @RolesAllowed({ "Member" })
    @Operation(summary = "Cancels a booking.", description = "Cancels a Booking and returns status code.")
    public Response cancel(Long id) {
        String jwtEmail = jwt.getClaim("email");
       return bookingService.cancel(id, jwtEmail);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Deletes a booking", description = "Deletes a booking and returns status code")
    public Response delete(Long id){
        return bookingService.delete(id);
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Update a booking", description = "Updates a booking and returns booking")
    @Transactional
    public Booking updatePut(Long id, Booking booking){
        return bookingService.update(id, booking);
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({ "Administrator" })
    @Operation(summary = "Update a booking partially", description = "Updates a booking partially and returns booking")
    @Transactional
    public Booking updatePatch(Long id, Booking booking){
        return bookingService.partialUpdate(id, booking);
    }
}
