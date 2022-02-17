package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.*;
import com.careerdevs.conqureTheWalk.models.auth.User;
import com.careerdevs.conqureTheWalk.repositories.*;
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
@RequestMapping("/api/dogs")
public class DogController {
    @Autowired
    private DogRepository repository;

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public @ResponseBody List<Dog> getAllDogs() {
       return repository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Dog getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/mydog")
    public @ResponseBody Set<Dog> getOwnDogs() {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return null;
        }
        Profile profile = profileRepository.getById(currentUser.getId());
        return profile.getMyDogs();

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

    @PostMapping("/photo/{dId}")
    public Dog addPhoto(@PathVariable Long dId, @RequestBody Dog avadog) {

        Dog dog = repository.findById(dId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (dog.getAvatar() != null) {
            Avatar avatar = dog.getAvatar();
            avatar.setUrl(avadog.getAvatar().getUrl());
            avatarRepository.save(avatar);
            return dog;
        }
        Avatar avatar = avatarRepository.save(avadog.getAvatar());
        dog.setAvatar(avatar);
        return repository.save(dog);

    }

//    @PutMapping("/breed/{dId}")
//    public @ResponseBody Dog addBreedToDog(@PathVariable Long dId, @RequestBody Breed dogBreed) {
//        Dog updateDog = repository.findById(dId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//
//    }

    @PutMapping("/{id}")
    public @ResponseBody Dog updateDog(@PathVariable Long id, @RequestBody Dog updates) {
        Dog updatedDog = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Breed breed = updates.getBreed();

        if (updates.getName() != null) updatedDog.setName(updates.getName());
        if (updates.getAge() != null) updatedDog.setAge(updates.getAge());
        if (updates.getWeight() != null) updatedDog.setWeight(updates.getWeight());
        if (updates.getSex() != null) updatedDog.setSex(updates.getSex());
        if (updates.getBreed() != null) updatedDog.setBreed(breed);

        breedRepository.save(breed);

        return repository.save(updatedDog);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyDog(@PathVariable long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
