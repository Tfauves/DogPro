package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.Entry;
import com.careerdevs.conqureTheWalk.models.Journal;
import com.careerdevs.conqureTheWalk.models.Profile;
import com.careerdevs.conqureTheWalk.repositories.EntryRepository;
import com.careerdevs.conqureTheWalk.repositories.JournalRepository;
import com.careerdevs.conqureTheWalk.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/journal")
public class JournalController {
    @Autowired
    private JournalRepository repository;
//get all journals

    @Autowired
    EntryRepository entry_repository;

    @GetMapping
    public List<Journal> getAll() {
        return repository.findAll();
    }
//get journal by id
    @GetMapping("/{journalId}")
    public @ResponseBody
    Journal getById(@PathVariable Long journalId) {
        return repository.findById(journalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
//create a journal
    @PostMapping
    public ResponseEntity<Journal> createJournal(@RequestBody Journal newJournal) {
        return new ResponseEntity<>(repository.save(newJournal), HttpStatus.CREATED);
    }

//update journal

    @PutMapping("/entry/{journalId}")
    public @ResponseBody Journal addEntry(@PathVariable Long journalId, @RequestBody Journal journalEntry) {
        Journal journal = repository.findById(journalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (journalEntry.getEntry() != null) journal.setEntry(journalEntry.getEntry());

        return repository.save(journal);
    }

    @PutMapping("/{id}")
    public @ResponseBody Journal updateJournal(@PathVariable Long id, @RequestBody Journal updateData) {
        Journal journal = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updateData.getEntry() != null) journal.setEntry(updateData.getEntry());
        if (updateData.getProfile() != null) journal.setProfile(updateData.getProfile());

        return repository.save(journal);
    }
//delete journal
    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyJournal(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
