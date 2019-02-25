package dal.contexts.memory;

import Exceptions.UserNotFoundException;
import dal.interfaces.UserDAO;
import models.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Alternative
@Stateful
public class UserMemoryDAO implements UserDAO {

    private static long userId = 0;
    private List<User> users = new ArrayList<>();

    public UserMemoryDAO() {
    }
//
//    public UserMemoryDAO(List<User> users) {
//        this.users = users;
//    }

    @Override
    public User add(User user) {
        User userToAdd = new User(userId,user.getUsername(),user.getPassword(),user.getPhoto(),user.getBio(),user.getLocation(),user.getWebsite(),user.getRoles(),user.getFollowing());
        this.users.add(userToAdd);
        userId++;
        return user;
    }

    @Override
    public boolean edit(User user) {

        for(User u : users){
            if(u.getId() == user.getId()){
                u = user;
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(long userId) {
        for (User u : users){
            if (u.getId() == userId){
                users.remove(u);
            }
        }
    }

    @Override
    public User getUserById(long id) {
        for (User u : users){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }

    @Override
    public List<User> users() {
        return this.users;
    }

    @Override
    public List<User> search(String username) {
        List<User> foundUsers = new ArrayList<>();
        for(User u : this.users){
            if (u.getUsername().toLowerCase().contains(username.toLowerCase())){
                foundUsers.add(u);
            }
        }
        return foundUsers;
    }

    @Override
    public User getUser(String username) throws UserNotFoundException {
        for (User u : this.users){
            if (u.getUsername().equalsIgnoreCase(username)){
                return u;
            }
        }
        throw new UserNotFoundException("Geen gebruiker gevonden met de gebruikersnaam: " + username + ".");
    }
}
