package com.careerdevs.conqureTheWalk.controllers;


import com.careerdevs.conqureTheWalk.models.Entry;
import com.careerdevs.conqureTheWalk.models.Journal;
import com.careerdevs.conqureTheWalk.models.Profile;
import com.careerdevs.conqureTheWalk.models.auth.User;
import com.careerdevs.conqureTheWalk.repositories.EntryRepository;
import com.careerdevs.conqureTheWalk.repositories.JournalRepository;
import com.careerdevs.conqureTheWalk.repositories.ProfileRepository;
import com.careerdevs.conqureTheWalk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/entry")
public class EntryController {
    @Autowired
    private EntryRepository repository;

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Entry> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Entry> createEntry(@RequestBody Entry newEntry) {

        Journal dogJournal = journalRepository.findById(newEntry.getJournal().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        dogJournal.setEntry(newEntry.getJournal().getEntry());
        newEntry.setActivity(newEntry.getActivity());
        journalRepository.save(dogJournal);
        return new ResponseEntity<>(repository.save(newEntry), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public @ResponseBody Entry updateEntry(@PathVariable Long id, Entry updateData) {
        Entry entry = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updateData.getActivity() != null) entry.setActivity(updateData.getActivity());
        if (updateData.getDuration() != null) entry.setDuration(updateData.getDuration());
//        if (updateData.getGoal() != null) entry.setGoal(updateData.getGoal());

        return repository.save(entry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyEntry(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
