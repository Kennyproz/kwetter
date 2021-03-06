package service;

import dal.interfaces.KweetDAO;
import models.Kweet;
import models.KweetConvertor;
import models.KweetCreator;
import models.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import java.util.List;

@Default
@Stateless
public class KweetService {

    @Inject
    private KweetDAO kweetDAO;


    /**
     * Creates an Kweet by converting the KweetCreator to a kweet
     *
     * @param kweetCreator
     * @param users
     * @return
     */
    public Kweet add(KweetCreator kweetCreator, List<User> users) {
        KweetConvertor kweetConvertor = new KweetConvertor();
        kweetConvertor.setUsers(users);

        Kweet kweetToAdd = null;
        kweetToAdd = kweetConvertor.convertToKweet(kweetCreator);

        return kweetDAO.add(kweetToAdd);
    }

    /**
     * Updates the kweet
     *
     * @param kweet
     * @return
     */
    public boolean edit(Kweet kweet) {
        return kweetDAO.edit(kweet);
    }

    /**
     * Deletes the kweet
     *
     * @param kweetId
     */
    public void remove(long kweetId) {
        Kweet kweet = getKweetById(kweetId);
        kweetDAO.remove(kweet);
    }

    /**
     * Deletes the kweet
     *
     * @param kweet
     */
    public void remove(Kweet kweet) {
        kweetDAO.remove(kweet);
    }

    /**
     * Returns all kweets of the user
     *
     * @param userId
     * @return
     */
    public List<Kweet> kweets(long userId) {
        return kweetDAO.userKweets(userId);
    }

    /**
     * Returns all kweets of the user
     *
     * @param username
     * @return
     */
    public List<Kweet> kweets(String username) {
        return kweetDAO.userKweets(username);
    }

    /**
     * Returns all kweets containing search
     *
     * @param search
     * @return
     */
    public List<Kweet> search(String search){
        return kweetDAO.searchKweets(search);
    }


    /**
     * Returns all kweets of the user and his/her following users
     *
     * @param userId
     * @return
     */
    public List<Kweet> timeline(long userId){
        return kweetDAO.timeline(userId);
    }


    /**
     * Returns a kweet with the given id
     *
     * @param id
     * @return
     */
    public Kweet getKweetById(long id){
        return kweetDAO.getKweetById(id);
    }
}
