package dal.contexts.memory;

import Exceptions.UserNotFoundException;
import dal.interfaces.UserDAO;
import models.User;

import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Default
@Stateful
public class UserMemoryDAO implements UserDAO {

    private List<User> users;

    public UserMemoryDAO() {
        this.users = new ArrayList<>();
    }

    public UserMemoryDAO(List<User> users) {
        this.users = users;
    }

    @Override
    public User add(User user) {
         this.users.add(user);
         return user;
    }

    @Override
    public boolean edit(User user) {

        return false;
    }

    @Override
    public void remove(User user) {
        this.users.remove(user);
    }

    @Override
    public List<User> users() {
        return this.users;
    }

    @Override
    public List<User> search(String username) {
        List<User> foundUsers = new ArrayList<>();
        for(User u : this.users){
            if (u.getUsername().equalsIgnoreCase(username)){
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
