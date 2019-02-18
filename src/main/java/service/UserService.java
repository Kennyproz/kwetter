package service;

import Exceptions.UserNotFoundException;
import dal.interfaces.UserDAO;
import models.User;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Default
@ApplicationScoped
public class UserService {


    @Inject
    private UserDAO userDAO;

    public  User add(User user){
        userDAO.add(user);
        return user;
    }

    public boolean edit(User user){
        return userDAO.edit(user);
    }
    public void remove(User user){
        userDAO.remove(user);
    }

    public User getUser (String username) throws UserNotFoundException {
        return userDAO.getUser(username);
    }

    public List<User> search(String username){
        return userDAO.search(username);
    }

    public List<User> users(){
        return userDAO.users();
    }

}
