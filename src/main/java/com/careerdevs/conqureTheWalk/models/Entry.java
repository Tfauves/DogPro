package com.careerdevs.conqureTheWalk.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String duration;
//    private String goal;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    @JsonIgnoreProperties("id")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "journal_id", referencedColumnName = "id")
//    @JsonIncludeProperties("id")
    @JsonIgnore
    private Journal journal;

    // TODO: 2/11/2022 create an entry using the activities already made.
    //  entry then needs to be added to the journal, thinking this should be done while entry id being created.
    public Entry() {}

    public Entry(Timestamp timestamp,Activity activity, String duration) {
        this.timestamp = timestamp;
        this.activity = activity;
        this.duration = duration;
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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }
}
