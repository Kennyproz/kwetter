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

    /**
     *  Inserts users to the database when starting the application
     */
    @PostConstruct
    private void addUsers() {
        try {
            roleService.add(new Role("Admin"));
            userService.add(new User("Ken","TestPWK","https://picsum.photos/200","This is the first user" ,"Brouwhuis","https://picsum.photos/200",null,null));
            userService.add(new User("Ralf","TestPWR","https://picsum.photos/200","This is the second user","Gemert","https://picsum.photos/200",null,null));
            userService.add(new User("Kenny","TestPWK","https://picsum.photos/200","This is the third user" ,"Eindhoven","https://picsum.photos/200",null,null));
            userService.add(new User("Ralfie","TestPWR","https://picsum.photos/200","This is the fourth user","Amsterdam","https://picsum.photos/200",null,null));

        } catch (UserExistsException e) {
            e.printStackTrace();
        } catch (RoleExistsException e) {
            e.printStackTrace();
        }
    }
}
