package dal.contexts.jpa;


import Exceptions.UserNotFoundException;
import dal.interfaces.UserDAO;
import models.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Default
@Named
@Stateless
public class UserJPADAO implements UserDAO {

    @PersistenceContext(unitName = "kwetter")
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
    public void remove(long userId) {
        User u = getUserById(userId);
        em.remove(u);
    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class,id);
    }

    @Override
    public List<User> users() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public List<User> search(String username) {
        return em.createQuery("SELECT u FROM User u WHERE u.username LIKE :name").setParameter("name",username).getResultList();
    }

    @Override
    public User getUser(String username) throws UserNotFoundException {

        return em.find(User.class,username);
      //  return em.createQuery("SELECT u FROM User u WHERE u.username = :username").setParameter("username",username).getFirstResult();
    }
}
