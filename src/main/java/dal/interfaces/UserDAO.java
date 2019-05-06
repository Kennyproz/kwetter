package dal.interfaces;

import Exceptions.UserExistsException;
import Exceptions.UserNotFoundException;
import models.Role;
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
    List<User> getFollowing(long id);
    List<User> getFollowers(long id);
    boolean isFollowing(long userId, long isFollowingUserId);
    User getFullUserById(long id);
    List<Role> getUserRoles(long id);


}
