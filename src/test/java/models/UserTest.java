package models;

import Exceptions.UserNotFoundException;
import dal.contexts.memory.UserMemoryDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private UserMemoryDAO context;
    private Role admin;
    List<Role> roles;

    @BeforeEach
    void setUp() {
        context = new UserMemoryDAO();
        admin = new Role("Admin");
        roles = new ArrayList();
        roles.add(admin);

        //Create 1 user with admin role
        context.add(new User("User 1","P@ssw0rd","photolink","This is the first user" ,"Over Here","https://www.example.com",roles,new ArrayList()));

        //Create 9 users without admin role
        for(int i = 2; i <= 10; i++){
            context.add(new User("User " + i,"P@ssw0rd","photolink","This is user " + i,"Over Here","https://www.example.com",new ArrayList(),new ArrayList()));
        }
    }

    @Test
    void follow() throws UserNotFoundException {
        User testUser = null;
        testUser = context.getUser("User 1");
        testUser.follow(context.getUser("User 2"));
        assertEquals(1,testUser.getFollowing().size());
    }

    @Test
    void unfollow() throws UserNotFoundException {
        User testUser = context.getUser("User 1");
        testUser.follow(context.getUser("User 2"));
        assertEquals(1,testUser.getFollowing().size());
        testUser.unfollow(context.getUser("User 2"));
        assertEquals(0,testUser.getFollowing().size());

    }

    @Test
    void search(){

        try {
            assertEquals(context.users().get(0),context.getUser("User 1"));
        } catch (UserNotFoundException e) {
        }
        assertThrows(UserNotFoundException.class, () -> {
            context.getUser("NonExistingUser");
        });
    }
}