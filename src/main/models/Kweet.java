package main.models;

import java.util.Date;

public class Kweet {

    private String content;
    private Date dateTime;
    private User creator;

    public Kweet(){

    }

    public Kweet(String content, Date dateTime, User creator) {
        this.content = content;
        this.dateTime = dateTime;
        this.creator = creator;
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
}
