package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.Dog;
import com.careerdevs.conqureTheWalk.models.Journal;
import com.careerdevs.conqureTheWalk.models.Profile;
import com.careerdevs.conqureTheWalk.repositories.DogRepository;
import com.careerdevs.conqureTheWalk.repositories.JournalRepository;
import com.careerdevs.conqureTheWalk.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileRepository repository;

    @Autowired
    private JournalRepository journal_repository;

    @Autowired
    private DogRepository dog_repository;

    //get all profiles
    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    List<Profile> getAll() {
        return repository.findAll();
    }

    //create a new profile
    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile newProfile) {
        return new ResponseEntity<>(repository.save(newProfile), HttpStatus.CREATED);
    }

    //create a new profile with a journal
    @PostMapping("/journal")
    public ResponseEntity<Profile> createProf(@RequestBody Profile newProfile) {
        Journal newJournal = journal_repository.save(newProfile.getJournal());
        newJournal.setProfile(newProfile);
        return new ResponseEntity<>(repository.save(newProfile), HttpStatus.CREATED);
    }

    //add a journal to an existing profile
    @PutMapping("/add/journal")
    public Profile addJournal(@RequestBody Profile pro) {
        Profile profile = repository.findById(pro.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Journal journal = journal_repository.save(pro.getJournal());
        profile.setJournal(journal);
        return repository.save(profile);
    }

    //add dog to profile
    @PutMapping("/dog")
    public Profile addDog(@RequestBody Profile pro) {
        Profile profile = repository.findById(pro.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (pro.getDogs() != null) profile.setDogs(pro.getDogs());

        return repository.save(profile);

    }

    //update a profile
    @PutMapping("{id}")
    public @ResponseBody Profile updateById(@PathVariable Long id, @RequestBody Profile updateData) {
        Profile profile = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updateData.getName() != null) profile.setName(updateData.getName());
        if (updateData.getJournal() != null) profile.setJournal(updateData.getJournal());
        if (updateData.getDogs() != null) profile.setDogs(updateData.getDogs());

        return repository.save(profile);

    }

    //delete profile
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> destroyProfile(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
