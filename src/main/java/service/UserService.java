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

    public  User add(User user) throws UserExistsException {
        userDAO.add(user);
        return user;
    }

    public boolean edit(User user){
        return userDAO.edit(user);
    }

    public void remove(long userId){
        userDAO.remove(userId);
    }

    public User getUser(String username) throws UserNotFoundException {
        return userDAO.getUser(username);
    }

    public User getUserById(long id){
        return userDAO.getUserById(id);
    }

    public List<User> search(String username){
        return userDAO.search(username);
    }

    public List<User> users(){
        return userDAO.users();
    }

}
