package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.EntryType;
import com.careerdevs.conqureTheWalk.repositories.EntryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/entryType")
public class EntryTypeController {

    @Autowired
    EntryTypeRepository repository;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EntryType> createEntryType(@RequestBody EntryType newType) {
        return new ResponseEntity<>(repository.save(newType), HttpStatus.CREATED);
    }

    @GetMapping
    public @ResponseBody List<EntryType> readAllEntryTypes() {
        return repository.findAll();
    }


}
