package service;


import Exceptions.RoleExistsException;
import Exceptions.UserExistsException;
import models.*;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

@Singleton
@Startup
public class StartUp {

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Inject
    private KweetService kweetService;


    public StartUp() {
    }

    /**
     * Inserts users to the database when starting the application
     */
    @PostConstruct
    private void settingUpDatabase() {
//        this.addUsers();
//        this.addKweets();
    }

    private void addUsers() {
        UserConvertor userConvertor = new UserConvertor();
        User user1 = new User("Ken","TestPWK","https://picsum.photos/200","This is the first user" ,"Brouwhuis","https://www.exmaple.com",null, new HashSet<>());
        User user2 = new User("Ralf","TestPWR","https://picsum.photos/200","This is the second user","Gemert","https://www.exmaple.com",null, new HashSet<>());
        User user3 = new User("Kenny","TestPWK","https://picsum.photos/200","This is the third user" ,"Eindhoven","https://www.exmaple.com",null, new HashSet<>());
        User user4 = new User("Ralfie","TestPWR","https://picsum.photos/200","This is the fourth user","Amsterdam","https://www.exmaple.com",null, new HashSet<>());
        user1.getFollowing().add(user2);
        user2.getFollowing().add(user3);
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
            for (int i = 1; i < 10; i++)
            {
                User u = new User("user"+i,"user"+i,"https://picsum.photos/200?grayscale?random="+i,"this is user " + i,"Somewhere","https://www.example.com",new HashSet<>(),new HashSet<>());
                UserCreator uc = userConvertor.convertToCreator(u);
                userService.add(uc);
            }
        } catch (UserExistsException e) {
            e.printStackTrace();
        } catch (RoleExistsException e) {
            e.printStackTrace();
        }
    }

    private void addKweets() {
        KweetCreator kweetCreator1 = new KweetCreator("The very first Kweet Yohoo" ,"Ken");
        KweetCreator kweetCreator2 = new KweetCreator("The very second Kweet Yohoo" ,"Ken");
        KweetCreator kweetCreator3 = new KweetCreator("The very third Kweet Yohoo" ,"Ken");
        KweetCreator kweetCreator4 = new KweetCreator("The very fourth Kweet Yohoo" ,"Ken");
        KweetCreator kweetCreator5 = new KweetCreator("The very fifth Kweet Yohoo" ,"Ken");
        kweetService.add(kweetCreator1,userService.users());
        kweetService.add(kweetCreator2,userService.users());
        kweetService.add(kweetCreator3,userService.users());
        kweetService.add(kweetCreator4,userService.users());
        kweetService.add(kweetCreator5,userService.users());
        for (int i = 1; i < 10; i++) {
            KweetCreator kweetCreator = new KweetCreator("A test kweet " + i ,"user1");
            kweetService.add(kweetCreator,userService.users());
        }

    }
}
