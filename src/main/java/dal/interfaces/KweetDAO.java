package dal.interfaces;

import models.Kweet;
import models.User;

import java.util.List;

public interface KweetDAO {
    boolean add(Kweet kweet);
    boolean edit(Kweet kweet);
    void remove(Kweet kweet);
    List<Kweet> userKweets(User user);


}
