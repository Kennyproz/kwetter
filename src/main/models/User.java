package main.models;


import java.util.ArrayList;
import java.util.List;

public class User {
    private String username, password, photo,bio,location,website;
    private List<Role> roles = new ArrayList<>();
    private List<User> following = new ArrayList<>();
    private List<Tweet> tweets = new ArrayList<>();

    public User(){

    }

    public User(String username, String password, String photo, String bio, String location, String website, List<Role> roles, List<User> following) {
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.roles = roles;
        this.following = following;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public void follow(User user){
        this.following.add(user);
    }

    public void unfollow(User user){
        this.following.remove(user);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", photo='" + photo + '\'' +
                ", bio='" + bio + '\'' +
                ", location='" + location + '\'' +
                ", website='" + website + '\'' +
                ", roles=" + roles +
                ", following=" + following +
                ", tweets=" + tweets +
                '}';
    }
}
