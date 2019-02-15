package dal.contexts.jpa;


import Exceptions.UserNotFoundException;
import dal.interfaces.UserDAO;
import models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserJPADAO implements UserDAO {

    @PersistenceContext
    private EntityManager em;
//
//    public UserJPADAO() {
//
//    }

    @Override
    public boolean add(User user) {
        return false;
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
