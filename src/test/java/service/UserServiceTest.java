package service;

import Exceptions.UserExistsException;
import dal.contexts.memory.UserMemoryDAO;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static UserService userService;
    private static UserMemoryDAO userMemoryDAO;

    private static User user;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        userMemoryDAO = new UserMemoryDAO();
        User u = Mockito.mock(User.class);
        Mockito.when(u.getUsername()).thenReturn("testUser");
        try {
            userMemoryDAO.add(u);
        } catch (UserExistsException e) {
            e.printStackTrace();
        }
    }

    @Test
    void add() throws UserExistsException {
        user = Mockito.mock(User.class);
        Mockito.when(user.getUsername()).thenReturn("test");
        userMemoryDAO.add(user);
        assertEquals(2,userMemoryDAO.users().size());
    }

    @Test
    void edit() {
    }


    @Test
    void getUser() {
    }

    @Test
    void search() {

    }

    @Test
    void users() {
        assertEquals(1,userMemoryDAO.users().size());
    }

    @Test
    void remove() {
        userMemoryDAO.remove(1);
        assertEquals(1,userMemoryDAO.users().size());
    }
}