package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KweetTest {
    KweetConvertor kweetConvertor;
    List<User> users;
    User user;
    User user1;

    @BeforeEach
    void setUp() {
        this.users = new ArrayList<>();
        user = Mockito.mock(User.class);
        user1 = Mockito.mock(User.class);
        users.add(user);
        users.add(user1);
        Mockito.when(user.getUsername()).thenReturn("Ken");
        Mockito.when(user1.getUsername()).thenReturn("Kenny");
        kweetConvertor = new KweetConvertor(users);
    }

    @Test
    void creatingHashList(){
        String content = "test test word #cool # cool #test";
        Set<Hashtag> hashtagSet = kweetConvertor.createHashtagList(content);
        assertEquals("cool",hashtagSet.stream().findFirst().get().getName());
        assertEquals("test",hashtagSet.stream().skip(1).findFirst().get().getName());
    }

    @Test
    void createMentionList(){
        String content = "this is a test, how are you doing @nonExistingPerson @Ken @Kenny ";
        Set<User> mentionSet = kweetConvertor.createMentionList(content);
        assertEquals("Ken",mentionSet.stream().filter(user -> user.getUsername().equals("Ken")).findFirst().get().getUsername());
        assertEquals("Kenny",mentionSet.stream().filter(user -> user.getUsername().equals("Kenny")).findFirst().get().getUsername());

    }



}