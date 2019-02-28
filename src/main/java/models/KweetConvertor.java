package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class KweetConvertor {



    public KweetConvertor() {

    }

    public Kweet convertToKweet(KweetCreator kweetCreator){

        String content = kweetCreator.getContent();
        Date date =  new Date(kweetCreator.getDateTime());
        User user = new User();

        createMentionList(kweetCreator.getContent());
        Set<User> mentions = null;


        Kweet kweet = new Kweet(content,date,user,mentions);
        return kweet;

    }

    public Set<User> createMentionList(String content){

        Set<User> users = new HashSet<>();
        for(String s : content.split(" ")){
            if(s.charAt(0) == '@'){
                System.out.println("User mentioned" + s);
                String username = s.substring(1);
                System.out.println(username);
            }
        }

        return null;
    }
}
