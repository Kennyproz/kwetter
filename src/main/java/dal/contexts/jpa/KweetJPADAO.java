package dal.contexts.jpa;

import dal.interfaces.KweetDAO;
import models.Kweet;
import models.User;
import sun.swing.BakedArrayList;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

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
        List<Object[]> allkweets = em.createNamedQuery("getKweetsById").setParameter("userid",userId).getResultList();
        return this.getKweetList(allkweets);
    }

    @Override
    public List<Kweet> userKweets(String username) {
        List<Object[]> allkweets = em.createNamedQuery("getKweetsByUsername").setParameter("username",username).getResultList();
        return this.getKweetList(allkweets);
    }

    @Override
    public List<Kweet> searchKweets(String search) {
        List<Kweet> kweets = em.createNamedQuery("searchKweets",Kweet.class).setParameter("search",'%'+search+'%').getResultList();
        return kweets;
    }

    @Override
    public List<Kweet> timeline(long userId) {
        List<Kweet> kweets = em.createNamedQuery("timelineKweets",Kweet.class).setParameter("id",userId).getResultList();
        List<Kweet> userKweets = this.userKweets(userId);
        kweets.addAll(userKweets);
        Collections.sort(kweets, new Comparator<Kweet>(){
            @Override
            public int compare(Kweet k1, Kweet k2) {
                return k2.getDateTime().compareTo(k1.getDateTime());
            }
        });
        return kweets;
    }

    private List<Kweet> getKweetList(List<Object[]> allKweets){
        List<Kweet> kweets = new ArrayList<>();
        for(Object[] kweet: allKweets){
            long id = (long)kweet[0];
            String content = (String)kweet[1];
            long creatorId = (long)kweet[2];
            Date date = (Date)kweet[3];
            String creator = (String)kweet[4];
            String creatorPhoto = (String)kweet[5];

            Kweet k = new Kweet(id,content,date,new User(creatorId,creator,creatorPhoto));
            kweets.add(k);
        }
        return kweets;
    }

}
