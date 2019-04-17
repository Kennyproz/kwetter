package models;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "getKweetsById", query = "SELECT k.id, k.content, k.creator.id, k.dateTime, k.creator.username, k.creator.photo FROM Kweet k WHERE k.creator.id = :userid"),
        @NamedQuery(name = "getKweetsByUsername", query = "SELECT k.id, k.content, k.creator.id, k.dateTime, k.creator.username, k.creator.photo FROM Kweet k WHERE k.creator.username = :username")

})
public class Kweet {


    @Id
    @GeneratedValue
    private long id;

    private String content;
    private Date dateTime;

    @ManyToOne
    private User creator;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> mentions;

    @OneToMany
    private Set<Hashtag> hashtags;

    public Kweet(){
    }


    public Kweet(String content, Date dateTime) {
        this.content = content;
        this.dateTime = dateTime;
    }

    public Kweet(String content, Date dateTime, User creator) {
        this.content = content;
        this.dateTime = dateTime;
        this.creator = creator;
    }

    public Kweet(long id, String content, Date dateTime, User creator) {
        this.id = id;
        this.content = content;
        this.dateTime = dateTime;
        this.creator = creator;
    }


    public Kweet(String content, Date dateTime, User creator, Set<User> mentions, Set<Hashtag> hashtags) {
        this.content = content;
        this.dateTime = dateTime;
        this.creator = creator;
        this.mentions = mentions;
        this.hashtags = hashtags;
    }

    public Kweet(long id,String content, Date dateTime, User creator,Set<User> mentions,Set<Hashtag> hashtags) {
        this.id = id;
        this.content = content;
        this.dateTime = dateTime;
        this.creator = creator;
        this.mentions = mentions;
        this.hashtags = hashtags;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getStringDateTime() {
        return dateTime.toString();
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public User getCreator() {
        return creator;
    }

    public Set<User> getMentions() {
        return mentions;
    }

    public void setMentions(Set<User> mentions) {
        this.mentions = mentions;
    }

    public void addMention(User user){
        this.mentions.add(user);
    }
}
