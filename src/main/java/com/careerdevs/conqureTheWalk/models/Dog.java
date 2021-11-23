package com.careerdevs.conqureTheWalk.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String breed;
    private String name;
    private Integer age;
    private String energyLvl;

    public Dog() {}

    public Dog(String breed, String name, Integer age, String energyLvl) {
        this.breed= breed;
        this.name = name;
        this.age = age;
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
}
