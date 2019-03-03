package models;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KweetCreator {
    private String content;
    private String dateTime;
    private String creator;
    private Set<String> mentions;
    private Set<String> hashtags;

    public KweetCreator(String content, String dateTime, String creator) {
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
