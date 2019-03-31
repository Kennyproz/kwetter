package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserConvertor {

    public UserConvertor() {

    }

    public User convertToUser(UserCreator userCreator){
        String username = userCreator.getUsername();
        String password = userCreator.getPassword();
        String bio = userCreator.getBio();
        String website = userCreator.getWebsite();
        String location = userCreator.getLocation();
        String photoLink = userCreator.getPhoto();

        User user = new User(username,password,photoLink,bio,location,website);
        return user;


    }

    public UserCreator convertToCreator(User user){
        Set<String> following = this.getUserIds(user.getFollowing());
        Set<String> roles = this.getRoleIds(user.getRoles());
        List<String> kweets = this.getKweetIds(user.getKweets());


        UserCreator userCreator = new UserCreator(user.getUsername(),user.getPassword(),user.getPhoto(),user.getBio(),user.getLocation(),user.getWebsite(),following,roles,kweets);
        return userCreator;

    }

    public Set<String> getRoleIds(Set<Role> roles){
        Set<String> list = new HashSet<>();
        if(roles != null && !roles.isEmpty()){
            for (Role r : roles){
                list.add(Long.toString(r.getId()));
            }
        }
        return list;
    }

    public Set<String> getUserIds(Set<User> users){
        Set<String> list = new HashSet<>();
        if(users != null &&  !users.isEmpty()){
            for (User u : users){
                list.add(Long.toString(u.getId()));
            }
        }
        return list;
    }
    public List<String> getKweetIds(List<Kweet> kweets){
        List<String> list = new ArrayList<>();
        if(kweets != null && !kweets.isEmpty()){
            for (Kweet k : kweets){
                list.add(Long.toString(k.getId()));
            }
        }
        return list;
    }
}
