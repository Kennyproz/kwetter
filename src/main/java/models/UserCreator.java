package models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class UserCreator implements Serializable {

    private static final long serialVersionUID = 4865903039190150223L;
    private String username, password, photo, bio, location, website;
    private Set<String> following;
    private Set<String> roles;
    private List<String> kweets;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoto() {
        return photo;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsite() {
        return website;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<String> getFollowing() {
        return following;
    }

    public void setFollowing(Set<String> following) {
        this.following = following;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public List<String> getKweets() {
        return kweets;
    }

    public void setKweets(List<String> kweets) {
        this.kweets = kweets;
    }

//    public List<Role> getRolesAsRole(){
//
//    }

    public UserCreator() {

    }

    public UserCreator(String username, String password, String photo, String bio, String location, String website) {
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.bio = bio;
        this.location = location;
        this.website = website;
    }

    public UserCreator(String username, String password, String photo, String bio, String location, String website, Set<String> following, Set<String> roles, List<String> kweets) {
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.following = following;
        this.roles = roles;
        this.kweets = kweets;
    }
}
