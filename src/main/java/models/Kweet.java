package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Kweet {


    @Id
    @GeneratedValue
    private long id;

    private String content;
    private Date dateTime;

    @ManyToOne
    private User creator;

    @ManyToMany
    private List<User> mentions;


    //private List<String> hashtags;

    public Kweet(){

    }

    public Kweet(String content, Date dateTime, User creator,List<User> mentions) {
        this.content = content;
        this.dateTime = dateTime;
        this.creator = creator;
        this.mentions = mentions;
    }

    public Kweet(long id,String content, Date dateTime, User creator,List<User> mentions) {
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

    public List<User> getMentions() {
        return mentions;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }
}
