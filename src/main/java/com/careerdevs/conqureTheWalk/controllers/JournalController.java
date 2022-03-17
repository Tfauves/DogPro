package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.Activity;
import com.careerdevs.conqureTheWalk.models.Entry;
import com.careerdevs.conqureTheWalk.models.Journal;
import com.careerdevs.conqureTheWalk.repositories.ActivityRepository;
import com.careerdevs.conqureTheWalk.repositories.EntryRepository;
import com.careerdevs.conqureTheWalk.repositories.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/journal")
public class JournalController {

    @Autowired
    private JournalRepository repository;

    @Autowired
    private EntryRepository entry_repository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
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
    public @ResponseBody Entry addEntry(@PathVariable Long journalId, @RequestBody Entry journalEntry) {
        Journal journal = repository.findById(journalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        journalEntry.setJournal(journal);

        repository.save(journal);

        return entry_repository.save(journalEntry);
    }

    @PutMapping("/{id}")
    public @ResponseBody Journal updateJournal(@PathVariable Long id, @RequestBody Journal updateData) {
        Journal journal = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

//        if (updateData.getEntry() != null) journal.setEntry(updateData.getEntry());
        if (updateData.getDog()!= null) journal.setDog(updateData.getDog());

        return repository.save(journal);
    }

    //delete journal
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> destroyJournal(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
