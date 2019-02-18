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

@Alternative
@Named("RoleJPA")
@Stateless
public class RoleJPADAO implements RoleDAO {


    @PersistenceContext
    private EntityManager em;
//
//    public RoleJPADAO(EntityManager em) {
//        this.em = em;
//    }

    @Override
    public boolean add(Role role) {
        return false;
    }

    @Override
    public void remove(Role role) {

    }

    @Override
    public List<Role> roles() {
        return null;
    }
}
