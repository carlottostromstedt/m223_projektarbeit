package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import ch.zli.m223.model.MeetingRoom;

@ApplicationScoped
public class MeetingRoomService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public MeetingRoom create(MeetingRoom meetingRoom) {
        entityManager.persist(meetingRoom);
        return meetingRoom;
    }

    public List<MeetingRoom> findAll() {
        var query = entityManager.createQuery("FROM MeetingRoom", MeetingRoom.class);
        return query.getResultList();
    }

    @Transactional
    public Response delete(Long id){
        MeetingRoom meetingRoom = entityManager.find(MeetingRoom.class, id);
        entityManager.remove(meetingRoom);
        return Response.status(204).build();
    }

    public MeetingRoom geMeetingRoom(Long id){
        return entityManager.find(MeetingRoom.class, id);
    }

    public MeetingRoom update(Long id, MeetingRoom meetingRoom){
        meetingRoom.setId(id);
        return entityManager.merge(meetingRoom);
    }

    // TODO: update this to do partialUpdate instead of full update
    public MeetingRoom partialUpdate(Long id, MeetingRoom meetingRoom){
        meetingRoom.setId(id);
        return entityManager.merge(meetingRoom);
    }

}
