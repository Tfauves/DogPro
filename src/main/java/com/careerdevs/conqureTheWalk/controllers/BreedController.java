package com.careerdevs.conqureTheWalk.controllers;


import com.careerdevs.conqureTheWalk.models.Breed;
import com.careerdevs.conqureTheWalk.repositories.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/breeds")
public class BreedController {
    @Autowired
    private BreedRepository repository;

    @GetMapping
    public List<Breed> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Breed> createBreed(@RequestBody Breed newBreed) {
        return new ResponseEntity<>(repository.save(newBreed), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public @ResponseBody Breed updatedBreed(@PathVariable Long id, @RequestBody Breed updateDate) {
        Breed breed = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (updateDate.getBreedType() != null) breed.setBreedType(updateDate.getBreedType());
        if (updateDate.getBreedGroup() != null) breed.setBreedGroup(updateDate.getBreedGroup());
        if (updateDate.getBreedAttributes() != null) breed.setBreedAttributes(updateDate.getBreedAttributes());

        return repository.save(breed);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyBreed(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
