package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "getUserById", query = "SELECT NEW models.User(u.id, u.username, u.password, u.photo, u.bio, u.location, u.website) FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "getUserByUsername", query = "SELECT NEW models.User(u.id, u.username, u.password, u.photo, u.bio, u.location, u.website) FROM User u WHERE u.username = :username"),
        @NamedQuery(name = "getUsers", query = "SELECT NEW models.User(u.id, u.username, u.password, u.photo, u.bio, u.location, u.website) FROM User u "),
        @NamedQuery(name = "getFollowingById", query = "SELECT following.id,following.username FROM User u JOIN u.following as following WHERE u.id = :id"),
        @NamedQuery(name = "getFollowersById", query = "SELECT u.id,u.username FROM User u JOIN u.following as following WHERE following.id = :id"),
        @NamedQuery(name = "isFollowing", query = "SELECT CASE WHEN (count(u) > 0) THEN TRUE ELSE FALSE END FROM User u JOIN u.following as following WHERE u.id = :userid AND following.id = :userfollowid"),
        @NamedQuery(name = "searchUsers", query = "SELECT NEW models.User(u.id, u.username, u.password, u.photo, u.bio, u.location, u.website) FROM User u WHERE u.username LIKE :name"),
})
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String username;
    private String password, photo, bio, location, website, token;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> following;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Kweet> kweets;


    @OneToMany
    private List<Link> links;
//    public List<Link> links = new ArrayList<>();

    public User() {
        this.kweets = new ArrayList<>();
    }

    public User(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(long id, String username, String photo) {
        this.id = id;
        this.username = username;
        this.photo = photo;
    }

    public User(String username, String photo, String bio, String location, String website) {
        this.username = username;
        this.photo = photo;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.kweets = new ArrayList<>();
        this.following = new HashSet<>();
    }

    public User(long id,String username,String password, String photo, String bio, String location, String website) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.bio = bio;
        this.location = location;
        this.website = website;
    }

    public User(long id,String username,String password, String photo, String bio, String location, String website, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.roles = roles;
    }

    public User(String username, String password, String photo, String bio, String location, String website) {
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.following = new HashSet<>();
        this.kweets = new ArrayList<>();
        this.roles = new HashSet<>();

    }

    public User(String username, String password, String photo, String bio, String location, String website, Set<Role> roles, Set<User> following) {
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.roles = roles;
        this.following = following;
        this.kweets = new ArrayList<>();

    }

    public User(long id, String username, String password, String photo, String bio, String location, String website, Set<Role> roles, Set<User> following) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.roles = roles;
        this.following = following;
        this.kweets = new ArrayList<>();

    }

    public long getId() {
        return id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<String> getRoleNames(){
        Set<String> stringRoles = new HashSet<>();
        if(this.roles != null){
            for(Role r : this.roles){
                stringRoles.add(r.getName());
            }
        }
        return stringRoles;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addKweet(Kweet kweet) {
        this.kweets.add(kweet);
    }

    public void follow(User user) {
        this.following.add(user);
    }

    public void unfollow(User user) {
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
                ", kweets=" + kweets +
                '}';
    }
}
