package main.dal.contexts.memory;

import main.Exceptions.UserNotFoundException;
import main.dal.interfaces.UserContext;
import main.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserMemoryContext implements UserContext {

    private List<User> users;

    public UserMemoryContext() {
        this.users = new ArrayList<>();
    }

    public UserMemoryContext(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean add(User user) {
        return this.users.add(user);
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
