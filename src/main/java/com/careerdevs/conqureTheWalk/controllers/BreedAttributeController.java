package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.BreedAttribute;
import com.careerdevs.conqureTheWalk.repositories.BreedAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/attributes")
public class BreedAttributeController {
    @Autowired
    BreedAttributeRepository repository;

    @GetMapping
    public @ResponseBody
    List<BreedAttribute> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<BreedAttribute> createAttribute(@RequestBody BreedAttribute newAttribute) {
        return new ResponseEntity<>(repository.save(newAttribute), HttpStatus.CREATED);
    }
}
