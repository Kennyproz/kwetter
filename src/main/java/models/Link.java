package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Link {

    @Id
    @GeneratedValue
    private long id;
    private String link;
    private String rel;


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public Link() {
    }

    public Link(String link, String rel) {
        this.link = link;
        this.rel = rel;
    }
}
