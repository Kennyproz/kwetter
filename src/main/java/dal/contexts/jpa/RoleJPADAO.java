package dal.contexts.jpa;


import Exceptions.RoleExistsException;
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
    public Role add(Role role) throws RoleExistsException {
        if(em.createQuery("SELECT r FROM Role r WHERE name LIKE :name").setParameter("name",role.getName()).getResultList().stream().findFirst().orElse(null) == null)
        {
            em.persist(role);
            return role;
        }
        throw new RoleExistsException("Rol met de naam: " + role.getName() + " kan niet worden toegevoegd omdat deze al bestaat.");
    }

    @Override
    public void remove(Role role) {
        em.remove(role);
    }

    @Override
    public Role getRoleById(long roleId) {
        return em.find(Role.class,roleId);
    }

    public Role getRoleByName(String name){
        return (Role) em.createQuery("SELECT r FROM Role r WHERE r.name LIKE :name").setParameter("name",name).getResultList().stream().findFirst().get();
    }

    @Override
    public List<Role> roles() {
        return em.createQuery("SELECT r FROM Role r").getResultList();
    }
}
