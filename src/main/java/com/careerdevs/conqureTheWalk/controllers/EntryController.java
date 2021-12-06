package com.careerdevs.conqureTheWalk.controllers;


import com.careerdevs.conqureTheWalk.models.Entry;
import com.careerdevs.conqureTheWalk.repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/entry")
public class EntryController {
    @Autowired
    private EntryRepository repository;

    @GetMapping
    public List<Entry> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Entry> createEntry(@RequestBody Entry newEntry) {
        return new ResponseEntity<>(repository.save(newEntry), HttpStatus.CREATED);
    }

}
