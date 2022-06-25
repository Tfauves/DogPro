package com.careerdevs.conqureTheWalk.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
// TODO: 6/24/2022 all of breed attribute/all journal/all entry
import javax.persistence.*;

@Entity
public class BreedAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String attributeName;
    private String attributeValue;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "breed_id", referencedColumnName = "id")
////    @JsonIncludeProperties("id")
//    private Breed breed;


    public BreedAttribute() {}

    public BreedAttribute(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

//    public Breed getBreed() {
//        return breed;
//    }
//
//    public void setBreed(Breed breed) {
//        this.breed = breed;
//    }
}
