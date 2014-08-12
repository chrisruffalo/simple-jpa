package com.chris.jpa.simple.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

/**
 * Simple model file for demonstration
 * 
 *
 */
@Entity
public class Person {

    @Id
    private String id;
    
    private String first;
    
    private String last;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }    
    
    /**
     * Used to implement custom ID logic.  This is 
     * basically the JPA equivalent of a pre-insert
     * trigger
     * 
     */
    @PrePersist
    private void createId() {
        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
