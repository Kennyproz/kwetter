package dal.contexts.jpa;

import dal.interfaces.KweetDAO;
import models.Kweet;
import models.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Default
@Named("KweetJPA")
@Stateless
public class KweetJPADAO implements KweetDAO {

    @PersistenceContext(unitName = "kwetter")
    private EntityManager em;
//
//

    @Override
    public boolean add(Kweet kweet) {
        em.persist(kweet);
        return true;
    }

    @Override
    public boolean edit(Kweet kweet) {
        return false;
    }

    @Override
    public void remove(Kweet kweet) {
        em.remove(kweet);

    }

    @Override
    public Kweet getKweetById(long id) {
        return null;
    }

    @Override
    public List<Kweet> userKweets(User user) {
        return null;
    }
}
