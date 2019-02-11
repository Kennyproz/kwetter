package main.dal.interfaces;

import main.models.Kweet;
import main.models.User;

import java.util.List;

public interface KweetContext {
    boolean add(Kweet kweet);
    boolean edit(Kweet kweet);
    void remove(Kweet kweet);
    List<Kweet> userKweets(User user);


}
