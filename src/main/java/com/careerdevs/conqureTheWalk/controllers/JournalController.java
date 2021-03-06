package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.Journal;
import com.careerdevs.conqureTheWalk.models.auth.User;
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


    @GetMapping
    public List<Journal> getAll() {
        return repository.findAll();
    }

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
        if (updateData.getDog()!= null) journal.setDog(updateData.getDog());

        return repository.save(journal);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> destroyJournal(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
