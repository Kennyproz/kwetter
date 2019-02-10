package main.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private List<User> users;
    private Role admin;
    List<Role> roles;

    @BeforeEach
    void setUp() {
        users = new ArrayList<>();
        admin = new Role("Admin");
        roles = new ArrayList<>();
        roles.add(admin);

        //Create 1 user with admin role
        users.add(new User("User 1","P@ssw0rd","photolink","This is the first user" ,"Over Here","https://www.example.com",roles,new ArrayList<>()));

        //Create 9 users without admin role
        for(int i = 2; i <= 10; i++){
            users.add(new User("User " + i,"P@ssw0rd","photolink","This is user " + i,"Over Here","https://www.example.com",new ArrayList<>(),new ArrayList<>()));
        }
    }

    @Test
    void follow() {
    }

    @Test
    void unfollow() {
    }
}