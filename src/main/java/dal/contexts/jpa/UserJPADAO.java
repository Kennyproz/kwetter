package dal.contexts.jpa;


import Exceptions.UserExistsException;
import dal.interfaces.UserDAO;
import models.Role;
import models.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Default
@Named
@Stateless
public class UserJPADAO implements UserDAO {

    @PersistenceContext(unitName = "kwetter")
    private EntityManager em;
    
    @Override
    public User add(User user) throws UserExistsException {
        if(this.checkIfUsernameExists(user.getUsername())){
            em.persist(user);
            return user;
        }
        throw new UserExistsException("Gebruiker met de naam: " + user.getUsername() + " kan niet worden toegevoegd omdat deze al bestaat.");
    }

    @Override
    public boolean edit(User user) {
        em.merge(user);
        return true;
    }

    @Override
    public void remove(long userId) {
        User u = getUserById(userId);
        em.remove(u);
    }

    @Override
    public User getUserById(long id) {
        return em.createNamedQuery("getUserById",User.class).setParameter("id",id).getSingleResult();
    }

    @Override
    public User getFullUserById(long id) {
        return em.createQuery("SELECT u FROM User u WHERE u.id = :id",User.class).setParameter("id",id).getSingleResult();
    }

    @Override
    public List<User> users() {
        return em.createNamedQuery("getUsers",User.class).getResultList();
    }

    @Override
    public List<User> search(String username) {
        return em.createNamedQuery("searchUsers",User.class).setParameter("name","%"+username + "%").getResultList();

    }

    @Override
    public User getUser(String username) {
        return em.createNamedQuery("getUserByUsername",User.class).setParameter("username",username).getSingleResult();
    }

    @Override
    public List<Role> getUserRoles(long id){
        return em.createQuery("SELECT NEW models.Role(r.id, r.name) FROM User u JOIN u.roles r WHERE u.id = :id ").setParameter("id",id).getResultList();
    }

    @Override
    public boolean checkIfUsernameExists(String username) {
        if(em.createQuery("SELECT NEW models.User(u.id, u.username, u.password, u.photo, u.bio, u.location, u.website) FROM User u WHERE username LIKE :name").setParameter("name",username).getResultList().stream().findFirst().orElse(null) != null)
        {
            return true;
        }
        return false;
    }

    @Override
    public User login(String username, String password) {
        User user = (User) em.createQuery("SELECT NEW models.User(u.id, u.username, u.password, u.photo, u.bio, u.location, u.website) FROM User u WHERE username LIKE :name AND password LIKE :password").setParameter("name",username).setParameter("password",password).getSingleResult();
        if(user != null){
            user.setRoles(new HashSet<Role>(this.getUserRoles(user.getId())));
            return user;
        }
        return null;
    }

    @Override
    public List<User> getFollowing(long id) {
         List<Object[]> users = em.createNamedQuery("getFollowingById").setParameter("id",id).getResultList();
         return this.getUserFollowings(users);
    }

    @Override
    public List<User> getFollowers(long id) {
        List<Object[]> users = em.createNamedQuery("getFollowersById").setParameter("id",id).getResultList();
        return this.getUserFollowings(users);
    }

    @Override
    public boolean isFollowing(long userId, long isFollowingUserId) {
        Object isFollowing =  em.createNamedQuery("isFollowing").setParameter("userid",userId).setParameter("userfollowid",isFollowingUserId).getSingleResult();
        if((boolean)isFollowing){
            return true;
        }
        return false;
    }

    private List<User> getUserFollowings(List<Object[]> users){
        List<User> followingUsers = new ArrayList<>();
        for(Object[] user: users){
            long id = (long)user[0];
            String username = (String)user[1];

            User u = new User(id,username);
            followingUsers.add(u);
        }
        return followingUsers;
    }


}
