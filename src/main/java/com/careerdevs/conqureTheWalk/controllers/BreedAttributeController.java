package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.BreedAttribute;
import com.careerdevs.conqureTheWalk.repositories.BreedAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PutMapping("/{id}")
    public @ResponseBody BreedAttribute updateAttribute(@PathVariable Long id, @RequestBody BreedAttribute updateData) {
        BreedAttribute attribute = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updateData.getAttributeName() != null) attribute.setAttributeName(updateData.getAttributeName());
        if (updateData.getAttributeValue() != null) attribute.setAttributeValue(updateData.getAttributeValue());

        return repository.save(attribute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyAttribute(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


}
