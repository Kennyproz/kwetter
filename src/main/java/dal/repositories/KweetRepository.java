package dal.repositories;

import dal.interfaces.KweetDAO;
import models.Kweet;
import models.User;

import java.util.List;

public class KweetRepository {

    private KweetDAO context;

    public KweetRepository(KweetDAO context) {
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
