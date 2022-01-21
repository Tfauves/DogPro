package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.Dog;
import com.careerdevs.conqureTheWalk.models.Journal;
import com.careerdevs.conqureTheWalk.models.Profile;
import com.careerdevs.conqureTheWalk.models.auth.User;
import com.careerdevs.conqureTheWalk.repositories.DogRepository;
import com.careerdevs.conqureTheWalk.repositories.JournalRepository;
import com.careerdevs.conqureTheWalk.repositories.ProfileRepository;
import com.careerdevs.conqureTheWalk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/dogs")
public class DogController {
    @Autowired
    private DogRepository repository;

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    UserService userService;

    @GetMapping
    public @ResponseBody List<Dog> getAllDogs() {
       return repository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Dog getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Dog> createDog(@RequestBody Dog newDog) {
        return new ResponseEntity<>(repository.save(newDog), HttpStatus.CREATED);
    }

    @PostMapping("/new")
    public ResponseEntity<Dog> createDogWithJournal(@RequestBody Dog newDog) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Profile profile = profileRepository.findByUser_id(currentUser.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Journal newJournal = journalRepository.save(newDog.getJournal());
        newJournal.setDog(newDog);
        newDog.setOwner(profile);

        return new ResponseEntity<>(repository.save(newDog), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public @ResponseBody Dog updateDog(@PathVariable Long id, @RequestBody Dog updates) {
        Dog updatedDog = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) updatedDog.setName(updates.getName());
        if (updates.getAge() != null) updatedDog.setAge(updates.getAge());
        if (updates.getWeight() != null) updatedDog.setWeight(updates.getWeight());
        if (updates.getSex() != null) updatedDog.setSex(updatedDog.getSex());
        if (updates.getOwner() != null) updatedDog.setOwner(updatedDog.getOwner());
        return repository.save(updatedDog);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyDog(@PathVariable long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
