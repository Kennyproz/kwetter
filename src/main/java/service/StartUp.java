package service;


import Exceptions.RoleExistsException;
import Exceptions.UserExistsException;
import models.Role;
import models.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartUp {

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    public StartUp() {
    }

    @PostConstruct
    private void addUsers() {
        try {
            roleService.add(new Role("Admin"));
            userService.add(new User("Ken","TestPWK","photolink","This is the first user" ,"Over Here","https://www.example.com",null,null));
            userService.add(new User("Ralf","TestPWR","photolink","This is the second user","Over there","https://www.example.com",null,null));
        } catch (UserExistsException e) {
            e.printStackTrace();
        } catch (RoleExistsException e) {
            e.printStackTrace();
        }
    }
}
