package dal.interfaces;

import Exceptions.UserExistsException;
import Exceptions.UserNotFoundException;
import models.Role;
import models.User;

import java.util.List;

public interface UserDAO {
    User add(User user) throws UserExistsException;
    boolean edit(User user);
    boolean checkIfUsernameExists(String username);
    boolean isFollowing(long userId, long isFollowingUserId);
    void remove(long userId);
    User getUser(String username) throws UserNotFoundException;
    User getUserById(long id);
    User getFullUserById(long id);
    User login(String username, String password);
    List<Role> getUserRoles(long id);
    List<User> search(String username);
    List<User> getFollowing(long id);
    List<User> getFollowers(long id);
    List<User> users();



}
