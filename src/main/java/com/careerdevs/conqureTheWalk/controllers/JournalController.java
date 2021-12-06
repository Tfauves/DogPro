package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.Journal;
import com.careerdevs.conqureTheWalk.models.Profile;
import com.careerdevs.conqureTheWalk.repositories.JournalRepository;
import com.careerdevs.conqureTheWalk.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/api/journal")
public class JournalController {
    @Autowired
    private JournalRepository repository;

    @GetMapping("/{journalId}")
    public @ResponseBody
    Journal getById(@PathVariable Long journalId) {
        return repository.findById(journalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Journal> createJournal(@RequestBody Journal newJournal) {
        return new ResponseEntity<>(repository.save(newJournal), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public @ResponseBody Journal updateJournal(@PathVariable Long id, @RequestBody Journal updateData) {
        Journal journal = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updateData.getEntry() != null) journal.setEntry(updateData.getEntry());

        return repository.save(journal);
    }

}
