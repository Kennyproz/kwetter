package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
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


    //private List<String> hashtags;

    public Kweet(){

    }

    public Kweet(String content, Date dateTime, User creator,Set<User> mentions) {
        this.content = content;
        this.dateTime = dateTime;
        this.creator = creator;
        this.mentions = mentions;
    }

    public Kweet(long id,String content, Date dateTime, User creator,Set<User> mentions) {
        this.id = id;
        this.content = content;
        this.dateTime = dateTime;
        this.creator = creator;
        this.mentions = mentions;
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
