package dal.contexts.jpa;


import dal.interfaces.RoleDAO;
import models.Role;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Default
@Named("RoleJPA")
@Stateless
public class RoleJPADAO implements RoleDAO {


    @PersistenceContext(unitName = "kwetter")
    private EntityManager em;

    @Override
    public boolean add(Role role) {
        em.persist(role);
        return true;
    }

    @Override
    public void remove(Role role) {
        em.remove(role);
    }

    @Override
    public Role getRoleById(int roleId) {
        return null;
    }

    @Override
    public List<Role> roles() {
        return null;
    }
}
