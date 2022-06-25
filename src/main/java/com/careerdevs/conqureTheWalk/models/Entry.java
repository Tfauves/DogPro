package com.careerdevs.conqureTheWalk.models;



import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String activity;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());


    @ManyToOne
    @JoinColumn(name = "journal_id", referencedColumnName = "id")
//    @JsonIncludeProperties("id")
//    @JsonIgnore
    private Journal journal;

    public Entry() {}

    public Entry(Timestamp timestamp, String type, String activity) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
