package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;


import ch.zli.m223.model.Role;

@ApplicationScoped
public class RoleService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Role create(Role role) {
        entityManager.persist(role);
        return role;
    }

    public List<Role> findAll() {
        var query = entityManager.createQuery("FROM Role", Role.class);
        return query.getResultList();
    }

    @Transactional
    public Response delete(Long id){
        Role role = entityManager.find(Role.class, id);
        entityManager.remove(role);
        return Response.status(204).build();
    }

    public Role getRole(Long id){
        return entityManager.find(Role.class, id);
    }

    public Role findRoleByType(String type){
        Role role = (Role) entityManager.createQuery(
            "SELECT r FROM Role r WHERE r.type LIKE :type")
            .setParameter("type", type)
            .getSingleResult();
        return role;
    }

    public Role update(Long id, Role role){
        role.setId(id);
        return entityManager.merge(role);
    }

}
