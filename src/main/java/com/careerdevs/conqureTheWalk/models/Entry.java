package com.careerdevs.conqureTheWalk.models;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private EntryType type;

    private String activity;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public Entry() {}

    public Entry(Timestamp timestamp, EntryType type, String activity) {
        this.timestamp = timestamp;
        this.activity = activity;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }
}
