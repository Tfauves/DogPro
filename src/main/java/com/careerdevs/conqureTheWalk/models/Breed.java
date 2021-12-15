package com.careerdevs.conqureTheWalk.models;

import javax.persistence.*;
import java.util.List;


@Entity
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String breedGroup;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "breed")
    private List<BreedAttribute> breedAttributes;


    public Breed() {}

    public Breed(String type, String breedGroup, List<BreedAttribute> breedAttributes) {
        this.type = type;
        this.breedGroup = breedGroup;
        this.breedAttributes = breedAttributes;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

    public List<BreedAttribute> getBreedAttributes() {
        return breedAttributes;
    }

    public void setBreedAttributes(List<BreedAttribute> breedAttributes) {
        this.breedAttributes = breedAttributes;
    }
}
