package dal.interfaces;

import Exceptions.UserExistsException;
import Exceptions.UserNotFoundException;
import models.User;

import java.util.List;

public interface UserDAO {
    User add(User user) throws UserExistsException;
    boolean edit(User user);
    void remove(long userId);
    User getUserById(long id);
    List<User> users();
    List<User> search(String username);
    User getUser(String username) throws UserNotFoundException;
    boolean checkIfUsernameExists(String username);
    User login(String username, String password);


}
