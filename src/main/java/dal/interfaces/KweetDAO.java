package dal.interfaces;

import models.Kweet;
import models.User;

import java.util.List;

public interface KweetDAO {
    Kweet add(Kweet kweet);
    boolean edit(Kweet kweet);
    void remove(Kweet kweet);
    Kweet getKweetById(long id);
    List<Kweet> userKweets(long userId);
    List<Kweet> userKweets(String username);
    List<Kweet> searchKweets(String search);
    List<Kweet> timeline(long userId);

}
