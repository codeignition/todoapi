package com.codeigniton.todoapi;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

/**
 * Created by sinister on 07/04/15.
 */

@Entity
public class Todo {
    @Id
    private ObjectId id;

    private String title;
    private boolean done;
    private Date createdAt = new Date();

    public Todo() {
    }

    public Todo(String title) {
        this.title = title;
        this.done = false;
        this.createdAt = new Date();
    }

    public Todo(String title, boolean done, Date createdAt) {
        this.title = title;
        this.done = done;
        this.createdAt = createdAt;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
