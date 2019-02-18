package service;

import dal.interfaces.KweetDAO;
import models.Kweet;
import models.User;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Default
@ApplicationScoped
public class KweetService {


    @Inject
    private KweetDAO kweetDAO;


    public boolean add(Kweet kweet){
        return kweetDAO.add(kweet);
    }

    public boolean edit(Kweet kweet){
        return kweetDAO.edit(kweet);
    }
    public void remove(Kweet kweet){
        kweetDAO.remove(kweet);
    }

    public List<Kweet> kweets(User user){
        return kweetDAO.userKweets(user);
    }
}
