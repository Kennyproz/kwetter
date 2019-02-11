package main.dal.repositories;

import main.dal.interfaces.KweetContext;
import main.models.Kweet;
import main.models.User;

import java.util.List;

public class KweetRepository {

    private KweetContext context;

    public KweetRepository(KweetContext context) {
        this.context = context;
    }

    boolean add(Kweet kweet){
        return context.add(kweet);
    }

    boolean edit(Kweet kweet){
        return context.edit(kweet);
    }
    void remove(Kweet kweet){
        context.remove(kweet);
    }

    List<Kweet> kweets(User user){
        return context.userKweets(user);
    }
}
