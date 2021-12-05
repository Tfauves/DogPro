package com.careerdevs.conqureTheWalk.models;

import javax.persistence.*;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Integer weight;
    private String sex;


    private String breed;


    private String energyLvl;

    public Dog() {}

    public Dog(String name, Integer age, Integer weight, String sex, String breed, String energyLvl) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
        this.breed = breed;
        this.energyLvl = energyLvl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getEnergyLvl() {
        return energyLvl;
    }

    public void setEnergyLvl(String energyLvl) {
        this.energyLvl = energyLvl;
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
}
