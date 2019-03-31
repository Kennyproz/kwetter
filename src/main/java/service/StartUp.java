package service;


import Exceptions.RoleExistsException;
import Exceptions.UserExistsException;
import models.Role;
import models.User;
import models.UserConvertor;
import models.UserCreator;

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
        UserConvertor userConvertor = new UserConvertor();

        User user1 = new User("Ken","TestPWK","https://picsum.photos/200","This is the first user" ,"Brouwhuis","https://www.exmaple.com",null,null);
        User user2 = new User("Ralf","TestPWR","https://picsum.photos/200","This is the second user","Gemert","https://www.exmaple.com",null,null);
        User user3 = new User("Kenny","TestPWK","https://picsum.photos/200","This is the third user" ,"Eindhoven","https://www.exmaple.com",null,null);
        User user4 = new User("Ralfie","TestPWR","https://picsum.photos/200","This is the fourth user","Amsterdam","https://www.exmaple.com",null,null);
        UserCreator userCreator1 = userConvertor.convertToCreator(user1);
        UserCreator userCreator2 = userConvertor.convertToCreator(user2);
        UserCreator userCreator3 = userConvertor.convertToCreator(user3);
        UserCreator userCreator4 = userConvertor.convertToCreator(user4);

        try {
            roleService.add(new Role("Admin"));
            userService.add(userCreator1);
            userService.add(userCreator2);
            userService.add(userCreator3);
            userService.add(userCreator4);

        } catch (UserExistsException e) {
            e.printStackTrace();
        } catch (RoleExistsException e) {
            e.printStackTrace();
        }
    }
}
