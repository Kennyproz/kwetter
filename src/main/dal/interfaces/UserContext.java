package main.dal.interfaces;

import main.Exceptions.UserNotFoundException;
import main.models.User;

import java.util.List;

public interface UserContext {
    boolean add(User user);
    boolean edit(User user);
    void remove(User user);
    List<User> users();
    List<User> search(String username);
    User getUser(String username) throws UserNotFoundException;

}
