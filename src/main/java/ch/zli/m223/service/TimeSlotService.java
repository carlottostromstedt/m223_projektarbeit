package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import ch.zli.m223.model.TimeSlot;

@ApplicationScoped
public class TimeSlotService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public TimeSlot create(TimeSlot timeSlot) {
        entityManager.persist(timeSlot);
        return timeSlot;
    }

    public List<TimeSlot> findAll() {
        var query = entityManager.createQuery("FROM TimeSlot", TimeSlot.class);
        return query.getResultList();
    }

    @Transactional
    public Response delete(Long id){
        TimeSlot timeSlot = entityManager.find(TimeSlot.class, id);
        entityManager.remove(timeSlot);
        return Response.status(204).build();
    }

    public TimeSlot getTimeSlot(Long id){
        return entityManager.find(TimeSlot.class, id);
    }

    public TimeSlot update(Long id, TimeSlot timeSlot){
        timeSlot.setId(id);
        return entityManager.merge(timeSlot);
    }
}
