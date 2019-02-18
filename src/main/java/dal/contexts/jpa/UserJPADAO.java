package dal.contexts.jpa;


import Exceptions.UserNotFoundException;
import dal.interfaces.UserDAO;
import models.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Alternative
@Named
@Stateless
public class UserJPADAO implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User add(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public boolean edit(User user) {
        return false;
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public List<User> users() {
        return null;
    }

    @Override
    public List<User> search(String username) {
        return null;
    }

    @Override
    public User getUser(String username) throws UserNotFoundException {
        return null;
    }
}
