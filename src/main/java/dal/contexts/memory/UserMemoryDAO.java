package dal.contexts.memory;

import Exceptions.UserExistsException;
import Exceptions.UserNotFoundException;
import dal.interfaces.UserDAO;
import models.Role;
import models.User;

import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Alternative
@Stateful
public class UserMemoryDAO implements UserDAO {

    private static long userId = 0;
    private List<User> users = new ArrayList<>();

    public UserMemoryDAO() {
    }

    /**
     *  Adds an user
     * @param user
     * @return Returns the added user
     * @throws UserExistsException
     */
    @Override
    public User add(User user) throws UserExistsException {
        User userToAdd = new User(userId,user.getUsername(),user.getPassword(),user.getPhoto(),user.getBio(),user.getLocation(),user.getWebsite(),user.getRoles(),user.getFollowing());
        if(users.size() > 0){
            for (User u : users){
                if(u.getUsername().equalsIgnoreCase(user.getUsername())){
                   throw new UserExistsException("Er bestaat al een gebruiker met de gebruikersnaam: " + user.getUsername());
                }
            }
        }
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
        User userToDelete = null;
        for (User u : users){
            if (u.getId() == userId){
                userToDelete = u;
            }
        }
        users.remove(userToDelete);
    }

    @Override
    public User getUserById(long userId) {
        for (User u : users){
            if(u.getId() == userId){
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

    @Override
    public boolean checkIfUsernameExists(String username) {
        for (User u : this.users){
            if (u.getUsername().equalsIgnoreCase(username)){
               return true;
            }
        }
        return false;
    }

    @Override
    public User login(String username, String password) {
        for (User u : this.users){
            if(u.getUsername().equals(username) && u.getPassword().equals(password))
                return u;
        }
        return null;
    }

    @Override
    public List<User> getFollowing(long userId) {
        for (User u : this.users){
            if(u.getId() == userId){
                return new ArrayList<User>(u.getFollowing());
            }
        }
        return null;
    }

    @Override
    public List<User> getFollowers(long userId) {
        List<User> followers = new ArrayList<>();
        for (User u : this.users){
            for(User follows : u.getFollowing() ){
                if(follows.getFollowing().contains(this.getUserById(userId))){
                    followers.add(follows);
                }
            }
        }
        return followers;
    }

    @Override
    public boolean isFollowing(long userId, long isFollowingUserId) {
        for(User u : getUserById(userId).getFollowing()){
            if(u.getId() == isFollowingUserId){
                return true;
            }
        }
        return false;
    }

    @Override
    public User getFullUserById(long userId) {
        return this.getUserById(userId);
    }

    @Override
    public List<Role> getUserRoles(long userId) {
        return new ArrayList<>(getFullUserById(userId).getRoles());
    }

}
