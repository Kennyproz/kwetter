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

    @Override
    public Kweet add(Kweet kweet) {
        em.persist(kweet);
        return kweet;
    }

    @Override
    public boolean edit(Kweet kweet) {
        em.merge(kweet);
        return true;
    }

    @Override
    public void remove(Kweet kweet) {
        em.remove(kweet);
    }

    @Override
    public Kweet getKweetById(long id) {
        return em.find(Kweet.class,id);
    }

    @Override
    public List<Kweet> userKweets(long userId) {
        return em.createQuery("SELECT k FROM Kweet k WHERE k.creator.id = :userid").setParameter("userid",userId).getResultList();
     //   return user.getKweets();
    }

    @Override
    public List<Kweet> userKweets(String username) {
    return em.createNamedQuery("getKweets",Kweet.class).setParameter("username",username).getResultList();
    }
}
