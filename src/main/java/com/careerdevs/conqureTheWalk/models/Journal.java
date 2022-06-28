package com.careerdevs.conqureTheWalk.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import javax.persistence.*;
import java.util.Set;


@Entity
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "dog_id", referencedColumnName = "id")
    @JsonIncludeProperties("id")
    private Dog dog;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "journal_id", referencedColumnName = "id")
    @JsonIgnoreProperties("id")
    public Set<Entry> entry;
    public Journal() {}

    public Journal(Dog dog) {
        this.dog = dog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Entry> getEntry() {
        return entry;
    }

    public void setEntry(Set<Entry> entry) {
        this.entry = entry;
    }


    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }


}
