package main.dal.interfaces;

import main.models.Tweet;
import main.models.User;

import java.util.List;

public interface TweetContext {
    boolean add(Tweet tweet);
    boolean edit(Tweet tweet);
    void remove(Tweet tweet);
    List<Tweet> userTweets(User user);


}
