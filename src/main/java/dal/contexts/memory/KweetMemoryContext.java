package dal.contexts.memory;

import dal.interfaces.KweetContext;
import models.Kweet;
import models.User;


import java.util.ArrayList;
import java.util.List;

public class KweetMemoryContext implements KweetContext {

    private List<Kweet> kweets;

    public KweetMemoryContext(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    @Override
    public boolean add(Kweet kweet) {
        return this.kweets.add(kweet);
    }

    @Override
    public boolean edit(Kweet kweet) {

        return false;
    }

    @Override
    public void remove(Kweet kweet) {
        this.kweets.remove(kweet);
    }

    @Override
    public List<Kweet> userKweets(User user) {
        List<Kweet> kweetsOfUser = new ArrayList<>();
        for(Kweet t : this.kweets){
            if(t.getCreator().getUsername().equalsIgnoreCase(user.getUsername())){
                kweetsOfUser.add(t);
            }
        }
        return kweetsOfUser;
    }
}
