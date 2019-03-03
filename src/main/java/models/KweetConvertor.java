package models;

import java.util.*;
import java.util.stream.Collectors;

public class KweetConvertor {

    List<User> users;

    public KweetConvertor(List<User> users) {
        this.users = users;
    }

    public Kweet convertToKweet(KweetCreator kweetCreator){

        String content = kweetCreator.getContent();
        Date date =  new Date(kweetCreator.getDateTime());
        User user = new User();

        Set<User> mentions = createMentionList(kweetCreator.getContent());
        Set<Hashtag> hashtags = createHashtagList(kweetCreator.getContent());

        Kweet kweet = new Kweet(content,date,user,mentions,hashtags);
        return kweet;

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
