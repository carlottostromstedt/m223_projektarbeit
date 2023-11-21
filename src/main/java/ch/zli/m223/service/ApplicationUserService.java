package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.identity.SecurityIdentity;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Role;

@ApplicationScoped
public class ApplicationUserService {
    @Inject
    private EntityManager entityManager;

    @Inject
    RoleService roleService;

    @Transactional
    public ApplicationUser create(ApplicationUser user) {
        List<ApplicationUser> users = this.findAll();
        if (users.size() == 0) {
            Role adminRole = roleService.findRoleByType("Administrator");
            user.setRole(adminRole);
        } else {
            Role memberRole = roleService.findRoleByType("Member");
            user.setRole(memberRole);
        }

        String password_unhashed = user.getPassword();
        String password_bcrypt = BcryptUtil.bcryptHash(password_unhashed);
        user.setPassword(password_bcrypt);
        entityManager.persist(user);
        return user;
    }

    public List<ApplicationUser> findAll() {
        var query = entityManager.createQuery("FROM ApplicationUser", ApplicationUser.class);
        return query.getResultList();
    }

    @Transactional
    public Response delete(Long id){
        ApplicationUser user = entityManager.find(ApplicationUser.class, id);
        entityManager.remove(user);
        return Response.status(200).build();
    }

    public ApplicationUser getApplicationUser(Long id){
        return entityManager.find(ApplicationUser.class, id);
    }

    public ApplicationUser getApplicationUser(String username){
        ApplicationUser user = (ApplicationUser) entityManager.createQuery(
            "SELECT u FROM ApplicationUser u WHERE u.username LIKE :username")
            .setParameter("username", username)
            .getSingleResult();
        return user;
    }

    public ApplicationUser getApplicationUserByMail(String email){
        ApplicationUser user = (ApplicationUser) entityManager.createQuery(
            "SELECT u FROM ApplicationUser u WHERE u.email LIKE :email")
            .setParameter("email", email)
            .getSingleResult();
        return user;
    }

    public ApplicationUser update(Long id, ApplicationUser user){
        user.setId(id);
        return entityManager.merge(user);
    }

    public ApplicationUser partialUpdate(Long id, ApplicationUser user){
        user.setId(id);
        return entityManager.merge(user);
    }
}
