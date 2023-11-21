package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import java.util.Set;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Booking.AcceptanceState;
import ch.zli.m223.model.Booking.State;

@ApplicationScoped
public class BookingService {
    @Inject
    private EntityManager entityManager;

    @Inject
    ApplicationUserService applicationUserService;

    @Transactional
    public Booking create(Booking booking) {
        entityManager.persist(booking);
        return booking;
    }

    public List<Booking> findAll() {
        var query = entityManager.createQuery("FROM Booking", Booking.class);
        return query.getResultList();
    }


    public List<Booking> findMemberBookings(String email) {
        List<Booking> bookings = entityManager.createQuery(
            "SELECT b FROM ApplicationUser au JOIN au.bookings b WHERE au.email = :email", Booking.class)
            .setParameter("email", email)
            .getResultList();
        return bookings;
    }

    @Transactional
    public Response delete(Long id){
        Booking booking = entityManager.find(Booking.class, id);
        entityManager.remove(booking);
        return Response.status(204).build();
    }

    @Transactional
    public Response accept(Long id){
        Booking booking = entityManager.find(Booking.class, id);
        booking.setAcceptanceState(AcceptanceState.ACCEPTED);
        entityManager.merge(booking);
        return Response.status(200).build();
    }

    @Transactional
    public Response decline(Long id){
        Booking booking = entityManager.find(Booking.class, id);
        booking.setAcceptanceState(AcceptanceState.DECLINED);
        entityManager.merge(booking);
        return Response.status(200).build();
    }

    @Transactional
    public Response cancel(Long id, String userEmail){
        Booking booking = entityManager.find(Booking.class, id);
        Set<ApplicationUser> participants = booking.getParticipants();
        ApplicationUser user = applicationUserService.getApplicationUserByMail(userEmail);

        if (participants.contains(user)){
            booking.setState(State.CANCELED);
            entityManager.merge(booking);
            return Response.status(200).build();
        } else {
            return Response.status(403).build();
        }
    }


    public Booking getBooking(Long id){
        return entityManager.find(Booking.class, id);
    }

    public Booking update(Long id, Booking booking){
        booking.setId(id);
        return entityManager.merge(booking);
    }

    // TODO: update this to do partialUpdate instead of full update
    public Booking partialUpdate(Long id, Booking booking){
        booking.setId(id);
        return entityManager.merge(booking);
    }

}
