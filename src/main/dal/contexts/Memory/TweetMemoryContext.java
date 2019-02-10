package main.dal.contexts.Memory;

import main.dal.interfaces.TweetContext;
import main.models.Tweet;
import main.models.User;


import java.util.ArrayList;
import java.util.List;

public class TweetMemoryContext implements TweetContext {

    private List<Tweet> tweets;

    public TweetMemoryContext(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public boolean add(Tweet tweet) {
        return this.tweets.add(tweet);
    }

    @Override
    public boolean edit(Tweet tweet) {

        return false;
    }

    @Override
    public void remove(Tweet tweet) {
        this.tweets.remove(tweet);
    }

    @Override
    public List<Tweet> userTweets(User user) {
        List<Tweet> tweetsOfUser = new ArrayList<>();
        for(Tweet t : this.tweets){
            if(t.getCreator().getUsername().equalsIgnoreCase(user.getUsername())){
                tweetsOfUser.add(t);
            }
        }
        return tweetsOfUser;
    }
}
