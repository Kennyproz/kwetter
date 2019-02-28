package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique =true)
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(long id , String name){
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
