package DAL;

import BO.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RoleDAO {
    private static EntityManager em;

    public RoleDAO(EntityManager em) {
        this.em = em;
    }

    public static void saveRole(Role role) {
        em.getTransaction().begin();
        em.persist(role);
        em.getTransaction().commit();
    }
    public Role getRole(Role role) {
        TypedQuery<Role> query =
                em.createQuery("SELECT r FROM Role r WHERE r.characterName =:characterName", Role.class);
        query.setParameter("characterName", role.getCharacterName());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
