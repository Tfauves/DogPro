package com.careerdevs.conqureTheWalk.models;

import javax.persistence.*;
import java.util.List;


@Entity
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String breedType;
    private String breedGroup;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "breed")
    private List<BreedAttribute> breedAttributes;


    public Breed() {}

    public Breed(String breedType, String breedGroup, List<BreedAttribute> breedAttributes) {
        this.breedType = breedType;
        this.breedGroup = breedGroup;
        this.breedAttributes = breedAttributes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreedType() {
        return breedType;
    }

    public void setBreedType(String breedType) {
        this.breedType = breedType;
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
