package main.models;

import junit.framework.Assert;
import main.Exceptions.UserNotFoundException;
import main.dal.contexts.memory.UserMemoryContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private UserMemoryContext context;
    private Role admin;
    List<Role> roles;

    @BeforeEach
    void setUp() {
        context = new UserMemoryContext();
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
        //assertEquals();
    }

    @Test
    void unfollow() {
    }

    @Test
    void search(){
        try{
            assertEquals(context.users().get(0),context.getUser("User 1"));
            context.getUser("NonExistingUser");
        } catch (UserNotFoundException e){
            assertEquals("Geen gebruiker gevonden met de gebruikersnaam: NonExistingUser.",e.getMessage());
        }
    }
}