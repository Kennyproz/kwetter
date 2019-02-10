package main.dal.repositories;

import main.dal.interfaces.TweetContext;
import main.models.Tweet;
import main.models.User;

import java.util.List;

public class TweetRepository {

    private TweetContext context;

    public TweetRepository(TweetContext context) {
        this.context = context;
    }

    boolean add(Tweet tweet){
        return context.add(tweet);
    }

    boolean edit(Tweet tweet){
        return context.edit(tweet);
    }
    void remove(Tweet tweet){
        context.remove(tweet);
    }

    List<Tweet> tweets(User user){
        return context.userTweets(user);
    }
}
