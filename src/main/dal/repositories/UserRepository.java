package main.dal.repositories;

import main.Exceptions.UserNotFoundException;
import main.dal.interfaces.UserContext;
import main.models.User;

import java.util.List;

public class UserRepository {

    private UserContext context;

    public UserRepository(UserContext context) {
        this.context = context;
    }

    boolean add(User user){
        return context.add(user);
    }

    boolean edit(User user){
        return context.edit(user);
    }
    void remove(User user){
        context.remove(user);
    }

    User getUser (String username) throws UserNotFoundException {
        return context.getUser(username);
    }

    List<User> search(String username){
        return context.search(username);
    }

    List<User> users(){
        return context.users();
    }
}
