package models;

import java.util.List;

public class KweetCreator {
    private String content;
    private String dateTime;
    private String creator;
    private List<String> mentions;

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

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }
}
