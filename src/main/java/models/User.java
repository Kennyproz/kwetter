package models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "getUsers", query = "SELECT u FROM User u"),
        @NamedQuery(name = "getFollowingById", query = "SELECT following.id,following.username FROM User u JOIN u.following as following WHERE u.id = :id"),
        @NamedQuery(name =  "isFollowing", query = "SELECT u FROM User u JOIN u.following as following WHERE u.id = :userid AND following.id = :userfollowid")



})
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String username;
    private String password, photo, bio, location, website;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "user_user",
//            joinColumns = @JoinColumn (name = "User_id"),
//            inverseJoinColumns = @JoinColumn(name = "following_id")
//    )
    private Set<User> following;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Kweet> kweets;

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

    }

    public User(String username, String password, String photo, String bio, String location, String website) {
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.kweets = new ArrayList<>();

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

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public List<Kweet> getKweets() {
        return kweets;
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
