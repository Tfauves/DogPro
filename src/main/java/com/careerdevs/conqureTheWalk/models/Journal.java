package com.careerdevs.conqureTheWalk.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "entry_id", referencedColumnName = "id")
    private List<Entry> entry;


    public Journal() {}

    public Journal(List<Entry> entry) {
        this.entry = entry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}
