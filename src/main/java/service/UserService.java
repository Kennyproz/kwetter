package service;

import Exceptions.UserExistsException;
import Exceptions.UserNotFoundException;
import dal.interfaces.UserDAO;
import models.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
@Stateless
public class UserService {


    @Inject
    private UserDAO userDAO;

    /**
     *  Creates an user.
     * @param user
     * @return Returns the added user
     * @throws UserExistsException
     */
    public  User add(User user) throws UserExistsException {
        userDAO.add(user);
        return user;
    }

    /**
     * Updates the user
     * @param user
     * @return
     */
    public boolean edit(User user){
        return userDAO.edit(user);
    }

    /**
     * Deletes the user
     * @param userId
     */
    public void remove(long userId){
        userDAO.remove(userId);
    }


    /**
     * Returns the user with the given username
     * @param username
     * @return User
     * @throws UserNotFoundException
     */
    public User getUser(String username) throws UserNotFoundException {
        return userDAO.getUser(username);
    }

    /**
     * Returns user by Id
     * @param id
     * @return User
     */
    public User getUserById(long id){
        return userDAO.getUserById(id);
    }

    /**
     * Searhes for the user based on username, returns all users containing parameter username in username
     * @param username
     * @return
     */
    public List<User> search(String username){
        return userDAO.search(username);
    }

    /**
     * Returns all users
     * @return
     */
    public List<User> users(){
        return userDAO.users();
    }


    /**
     * Makes user with id userId follow the user with id userToFollowId
     * @param userId
     * @param userToFollowId
     * @return
     */
    public User follow(long userId, long userToFollowId){
        User user = getUserById(userId);
        User userToFollow = getUserById(userToFollowId);
        user.getFollowing().add(userToFollow);
        userDAO.edit(user);
        return user;
    }

    /**
     * Makes the user with id userId unfollow the user with id userToUnfollowId
     * @param userId
     * @param userToUnfollowId
     * @return
     */
    public User unfollow(long userId, long userToUnfollowId){
        User user = getUserById(userId);
        User userToUnfollow = getUserById(userToUnfollowId);
        user.getFollowing().remove(userToUnfollow);
        userDAO.edit(user);
        return user;

    }

    public User login(String username, String password){
        return userDAO.login(username,password);
    }

}
