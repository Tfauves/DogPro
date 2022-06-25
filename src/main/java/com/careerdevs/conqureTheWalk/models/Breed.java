package com.careerdevs.conqureTheWalk.models;

import javax.persistence.*;



@Entity
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String breedName;
    private String breedGroup;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("id")
//    private Set<BreedAttribute> breedAttributes;


    public Breed() {}

    public Breed(String breedName, String breedGroup) {
        this.breedName = breedName;
        this.breedGroup = breedGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

//    public Set<BreedAttribute> getBreedAttributes() {
//        return breedAttributes;
//    }
//
//    public void setBreedAttributes(Set<BreedAttribute> breedAttributes) {
//        this.breedAttributes = breedAttributes;
//    }
}
