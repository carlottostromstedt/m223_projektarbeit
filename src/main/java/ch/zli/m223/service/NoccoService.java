package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import ch.zli.m223.model.Nocco;

@ApplicationScoped
public class NoccoService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Nocco create(Nocco nocco) {
        entityManager.persist(nocco);
        return nocco;
    }

    public List<Nocco> findAll() {
        var query = entityManager.createQuery("FROM Nocco", Nocco.class);
        return query.getResultList();
    }

    @Transactional
    public Response delete(Long id){
        Nocco nocco = entityManager.find(Nocco.class, id);
        entityManager.remove(nocco);
        return Response.status(204).build();
    }

    public Nocco getNocco(Long id){
        return entityManager.find(Nocco.class, id);
    }

    public Nocco update(Long id, Nocco nocco){
        nocco.setId(id);
        return entityManager.merge(nocco);
    }

    // TODO: update this to do partialUpdate instead of full update
    public Nocco partialUpdate(Long id, Nocco nocco){
        nocco.setId(id);
        return entityManager.merge(nocco);
    }

}
