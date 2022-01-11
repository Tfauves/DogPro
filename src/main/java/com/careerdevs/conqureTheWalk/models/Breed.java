package com.careerdevs.conqureTheWalk.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String breedType;
    private String breedGroup;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "breed")
    private Set<BreedAttribute> breedAttributes;


    public Breed() {}

    public Breed(String breedType, String breedGroup) {
        this.breedType = breedType;
        this.breedGroup = breedGroup;
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

    public Set<BreedAttribute> getBreedAttributes() {
        return breedAttributes;
    }

    public void setBreedAttributes(Set<BreedAttribute> breedAttributes) {
        this.breedAttributes = breedAttributes;
    }
}
