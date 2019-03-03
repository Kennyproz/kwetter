package models;

import java.io.Serializable;
import java.util.Set;

public class KweetCreator implements Serializable {

    private static final long serialVersionUID = 4865903039190150223L;
    private String content;
    private String datetime;
    private String creator;
    private Set<String> mentions;
    private Set<String> hashtags;

    public KweetCreator() {
    }

    public KweetCreator(String content, String datetime, String creator) {
        this.content = content;
        this.datetime = datetime;
        this.creator = creator;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Set<String> getMentions() {
        return mentions;
    }

    public void setMentions(Set<String> mentions) {
        this.mentions = mentions;
    }

    public Set<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<String> hashtags) {
        this.hashtags = hashtags;
    }
}
