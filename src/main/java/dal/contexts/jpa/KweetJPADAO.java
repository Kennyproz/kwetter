package dal.contexts.jpa;

import dal.interfaces.KweetDAO;
import models.Kweet;
import models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class KweetJPADAO implements KweetDAO {

    @PersistenceContext
    private EntityManager em;
//
//

    @Override
    public boolean add(Kweet kweet) {
        return false;
    }

    @Override
    public boolean edit(Kweet kweet) {
        return false;
    }

    @Override
    public void remove(Kweet kweet) {

    }

    @Override
    public List<Kweet> userKweets(User user) {
        return null;
    }
}
