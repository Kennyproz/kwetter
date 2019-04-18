package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hashtag {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String name;


    private long count;

    public Hashtag() {
    }

    public Hashtag(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    //  private Map<String,Integer> hashtags;


}
