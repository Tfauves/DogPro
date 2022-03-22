package com.careerdevs.conqureTheWalk.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Integer weight;
    private String sex;


    @OneToOne
    @JsonIgnoreProperties("id")
    private Breed breed;

    @OneToOne
    @JsonIgnoreProperties("id")
    private Avatar avatar;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIncludeProperties("id")
    private Profile owner;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "dog")
    @JoinColumn(name = "journal_id", referencedColumnName = "id")
    @JsonIgnoreProperties("dog")
    private Journal journal;

    public Dog() {}

    public Dog(String name, Integer age, Integer weight, String sex, Profile owner, Journal journal) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
        this.owner = owner;
        this.journal = journal;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

//    public Set<Breed> getBreed() {
//        return breed;
//    }
//
//    public void setBreed(Set<Breed> breed) {
//        this.breed = breed;
//    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }
}
