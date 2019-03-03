package dal.contexts.memory;

import dal.interfaces.KweetDAO;
import models.Hashtag;
import models.Kweet;
import models.User;


import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Alternative
@Stateful
public class KweetMemoryDAO implements KweetDAO {

    private List<Kweet> kweets;
    private Map<Hashtag, Integer> hashtags;
    private static long kweetId = 0;

    public KweetMemoryDAO() {
    }

    public KweetMemoryDAO(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    @Override
    public Kweet add(Kweet kweet) {
        this.kweets.add(kweet);
        return kweet;
    }

    @Override
    public boolean edit(Kweet kweet){

        return false;
    }

    @Override
    public void remove(Kweet kweet) {
        this.kweets.remove(kweet);
    }

    @Override
    public Kweet getKweetById(long id) {
        for( Kweet k : kweets){
            if (k.getId() == id) {
                return k;
            }
        }
        return null;
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
