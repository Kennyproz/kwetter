package bean;

import dal.contexts.memory.UserMemoryDAO;
import models.User;
import service.UserService;

import javax.inject.Inject;

import java.io.Serializable;
import java.util.List;


public class UserBean implements Serializable {


    private static final long serialVersionUID =1L;

    @Inject
    private UserService userService;

    private User user;

//    public User getUser(){
//        user = new User("Kenny","testphoto","Bio","Loc","www.test.nl");
//        return user;
//    }

    public List<User> getUsers(){
        return userService.users();
    }
}
