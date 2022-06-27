package com.careerdevs.conqureTheWalk.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import javax.persistence.*;


@Entity
@Table(name = "dog")
// @SQLDelete annotation to override the delete command.
// that changes the deleted field value to true instead of deleting the data permanently.
@SQLDelete(sql = "UPDATE dog SET deleted = true WHERE id =?")
//still want the deleted data to be accessible.
//these annotations can dynamically add conditions as needed:
//@FilterDef annotation defines the basic requirements that will be used by @Filter annotation
@FilterDef(name = "deletedDogFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedDogFilter", condition = "deleted = :isDeleted")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Integer weight;
    private String sex;

    //deleted property with the default value set as FALSE
    private Boolean deleted = Boolean.FALSE;

    @OneToOne
    @JsonIgnoreProperties("id")
    private Breed breed;

    @OneToOne(cascade = CascadeType.ALL)
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

    public Dog(String name, Avatar avatar, Integer age, Integer weight, String sex, Profile owner, Journal journal, Breed breed) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
        this.owner = owner;
        this.journal = journal;
        this.breed = breed;
        this.avatar = avatar;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
