package dal.interfaces;

import Exceptions.UserNotFoundException;
import models.User;

import java.util.List;

public interface UserDAO {
    User add(User user);
    boolean edit(User user);
    void remove(User user);
    List<User> users();
    List<User> search(String username);
    User getUser(String username) throws UserNotFoundException;

}
