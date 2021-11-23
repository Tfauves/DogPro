package com.careerdevs.conqureTheWalk.controllers;


import com.careerdevs.conqureTheWalk.models.Dog;
import com.careerdevs.conqureTheWalk.repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/dogs")
public class DogController {
    @Autowired
    private DogRepository repository;

    @GetMapping
    public @ResponseBody List<Dog> geyAllDogs() {
       return repository.findAll();
    }
}
