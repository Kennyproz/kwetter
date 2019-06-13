package service;

import Exceptions.UserExistsException;
import Exceptions.UserNotFoundException;
import dal.interfaces.UserDAO;
import models.Role;
import models.User;
import models.UserConvertor;
import models.UserCreator;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Default
@Stateless
public class UserService {


    @Inject
    private UserDAO userDAO;

    /**
     * Creates an user.
     *
     * @param userCreator
     * @return Returns the added user
     * @throws UserExistsException
     */
    public User add(UserCreator userCreator) throws UserExistsException {
        UserConvertor userConvertor = new UserConvertor();
        User user = userConvertor.convertToUser(userCreator);
        userDAO.add(user);
        return user;
    }

    /**
     * Updates the user
     *
     * @param user
     * @return
     */
    public boolean edit(User user) {
        User editedUser = this.getFullUserById(user.getId());
        if(!this.userDAO.checkIfUsernameExists(user.getUsername())){
            editedUser.setUsername(user.getUsername());
        }
        return userDAO.edit(editedUser);
    }

    /**
     * Deletes the user
     *
     * @param userId
     */
    public void remove(long userId) {
        userDAO.remove(userId);
    }


    /**
     * Returns the user with the given username
     *
     * @param username
     * @return User
     * @throws UserNotFoundException
     */
    public User getUser(String username) throws UserNotFoundException {
        return userDAO.getUser(username);
    }

    /**
     * Returns the followers of user with id id
     *
     * @param userId
     * @return User
     */
    public List<User> getFollowersById(long userId){
        return userDAO.getFollowers(userId);
    }

    /**
     * Returns the following users of user with id id
     *
     * @param userId
     * @return User
     */
    public List<User> getFollowingById(long userId){
        return userDAO.getFollowing(userId);
    }

    /**
     * Returns userDTO by Id
     *
     * @param userId
     * @return User
     */
    public User getUserById(long userId) {
        return userDAO.getUserById(userId);
    }

    /**
     * Returns user by Id
     *
     * @param id
     * @return User
     */
    public User getFullUserById(long id){
        return userDAO.getFullUserById(id);
    }

    /**
     * Searhes for the user based on username, returns all users containing parameter username in username
     *
     * @param username
     * @return
     */
    public List<User> search(String username) {
        return userDAO.search(username);
    }

    /**
     * Returns all users
     *
     * @return
     */
    public List<User> users() {
        return userDAO.users();
    }


    /**
     * Makes user with id userId follow the user with id userToFollowId
     *
     * @param userId
     * @param userToFollowId
     * @return
     */
    public User follow(long userId, long userToFollowId) {
        User user = getFullUserById(userId);
        User userToFollow = getFullUserById(userToFollowId);
        user.getFollowing().add(userToFollow);
        userDAO.edit(user);
        return user;
    }

    /**
     * Returns the user roles
     *
     * @param userId
     * @return User
     */
    public List<Role> getUserRoles(long userId){
        return userDAO.getUserRoles(userId);
    }

    /**
     * Makes the user with id userId unfollow the user with id userToUnfollowId
     *
     * @param userId
     * @param userToUnfollowId
     * @return
     */
    public User unfollow(long userId, long userToUnfollowId) {
        User user = getFullUserById(userId);
        User userToUnfollow = getFullUserById(userToUnfollowId);
        user.getFollowing().remove(userToUnfollow);
        userDAO.edit(user);
        return user;
    }

    /**
     * Logs the user in.
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    /**
     * Check if user with id userId is following user with id userFollowId
     *
     * @param userId
     * @param userFollowId
     * @return
     */
    public boolean isFollowing(long userId, long userFollowId){
        return userDAO.isFollowing(userId,userFollowId);
    }

    public boolean checkUsername(String username){
        return userDAO.checkIfUsernameExists(username);
    }

}
