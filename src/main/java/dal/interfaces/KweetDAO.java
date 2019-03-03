package dal.interfaces;

import models.Kweet;
import models.User;

import java.util.List;

public interface KweetDAO {
    Kweet add(Kweet kweet);
    boolean edit(Kweet kweet);
    void remove(Kweet kweet);
    Kweet getKweetById(long id);
    List<Kweet> userKweets(User user);


}
