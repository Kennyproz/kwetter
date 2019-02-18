package dal.contexts.memory;

import dal.interfaces.KweetDAO;
import models.Kweet;
import models.User;


import javax.ejb.Stateful;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Default
@Stateful
public class KweetMemoryDAO implements KweetDAO {

    private List<Kweet> kweets;

    public KweetMemoryDAO() {
    }

    public KweetMemoryDAO(List<Kweet> kweets) {
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
