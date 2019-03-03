package models;

import javax.ejb.Stateless;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class KweetConvertor {

    List<User> users;

    public KweetConvertor() {
    }

    public KweetConvertor(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Kweet convertToKweet(KweetCreator kweetCreator){

        String content = kweetCreator.getContent();
        Date date = new Date();// new Date(kweetCreator.getDatetime());
        User user = this.getUserFromUsername(kweetCreator.getCreator());

        Set<User> mentions = createMentionList(kweetCreator.getContent());
        Set<Hashtag> hashtags = createHashtagList(kweetCreator.getContent());

        Kweet kweet = new Kweet(content,date,user,mentions,hashtags);
        return kweet;

    }


    private User getUserFromUsername(String username){
        for (User u : users){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }


    public Set<User> createMentionList(String content){

        Set<User> mentions = new HashSet<>();
        for(String s : content.split(" ")){
            if(s.charAt(0) == '@'){
                String username = s.substring(1);
                Optional<User> matchingObject = users.stream().
                        filter(u -> u.getUsername().equals(username)).
                        findFirst();
                User u = matchingObject.orElse(null);
                if(u != null){
                    mentions.add(u);
                }
            }
        }

        return mentions;
    }

    public Set<Hashtag> createHashtagList(String content){
        Set<Hashtag> hashtags = new HashSet<>();
        for(String s : content.split(" ")){
            if(s.charAt(0) == '#'){
                if(s.length() > 1){
                    String hashtag = s.substring(1);
                    hashtags.add(new Hashtag(hashtag));
                }
            }
        }
        return hashtags;
    }
}
