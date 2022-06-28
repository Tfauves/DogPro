package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.Entry;
import com.careerdevs.conqureTheWalk.models.EntryType;
import com.careerdevs.conqureTheWalk.models.Journal;
import com.careerdevs.conqureTheWalk.repositories.EntryRepository;
import com.careerdevs.conqureTheWalk.repositories.EntryTypeRepository;
import com.careerdevs.conqureTheWalk.repositories.JournalRepository;
import com.careerdevs.conqureTheWalk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/entry")
public class EntryController {
    @Autowired
    private EntryRepository repository;

    @Autowired
    EntryTypeRepository entryTypeRepository;

    @Autowired
    JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Entry> getAll() {
        return repository.findAll();
    }

    @PostMapping("/{jId}")
    public ResponseEntity<Entry> createEntry(@PathVariable Long jId, @RequestBody Entry newEntry) {
        Journal journal = journalRepository.findById(jId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        EntryType type = entryTypeRepository.findById(newEntry.getType().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        newEntry.setType(type);
        journal.entry.add(newEntry);

        return new ResponseEntity<>(repository.save(newEntry), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public @ResponseBody Entry updateEntry(@PathVariable Long id, Entry updateData) {
        Entry entry = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updateData.getType() != null) entry.setType(updateData.getType());
        if (updateData.getActivity() != null) entry.setActivity(updateData.getActivity());
        return repository.save(entry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyEntry(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
