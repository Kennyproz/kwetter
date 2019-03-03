package dal.contexts.memory;

import Exceptions.UserExistsException;
import Exceptions.UserNotFoundException;
import models.Role;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserMemoryDAOTest {


    private UserMemoryDAO context;
    private Role admin;
    Set<Role> roles;

    @BeforeEach
    void setUp() throws UserExistsException, UserNotFoundException {
        context = new UserMemoryDAO();
        admin = new Role("Admin");
        roles = new HashSet<>();
        roles.add(admin);

        //Create 1 user with admin role
        context.add(new User("User 1","P@ssw0rd","photolink","This is the first user" ,"Over Here","https://www.example.com",roles,new HashSet<>()));

        //Create 9 users without admin role
        for(int i = 2; i <= 10; i++){
            context.add(new User("User " + i,"P@ssw0rd","photolink","This is user " + i,"Over Here","https://www.example.com",new HashSet<>(),new HashSet<>()));
        }
    }

    @Test
    void follow() throws UserNotFoundException {
        User testUser;
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

    @Test
    void edit() throws UserNotFoundException {
        User testUser = context.getUser("User 1");
        assertEquals("This is the first user",testUser.getBio());
        String newbio = "New bio, this is an admin";
        testUser.setBio(newbio);
        context.edit(testUser);
        User newBioUser = context.getUser("User 1");
        assertEquals(newbio,newBioUser.getBio());

    }

    @Test
    void remove() {
        assertEquals(10,context.users().size());
        int size = context.users().size();
        User u = context.users().get(size - 1);
        context.remove(u.getId());
        assertEquals(size - 1,context.users().size());

    }
}