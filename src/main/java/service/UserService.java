package service;

import Exceptions.UserNotFoundException;
import models.User;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserService {

    boolean add(User user){
        return false;
    }

    boolean edit(User user){
        return false;
    }
    void remove(User user){


    }

    User getUser (String username) throws UserNotFoundException {
        return null;
    }

    List<User> search(String username){
        return null;
    }

    List<User> users(){
        return null;
    }

}
