package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.*;
import com.careerdevs.conqureTheWalk.models.auth.User;
import com.careerdevs.conqureTheWalk.repositories.*;
import com.careerdevs.conqureTheWalk.services.UserService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
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

    @Autowired
    EntityManager entityManager;

    @GetMapping("/all")
    public @ResponseBody List<Dog> getAllDogs() {
       return repository.findAll();
    }

    @GetMapping
    public Iterable<Dog> readAllProfile(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedDogFilter");
        //Here we add the isDeleted parameter that we'll add to the object Filter affecting the process of reading the Product entity.
        filter.setParameter("isDeleted", isDeleted);
        Iterable<Dog> dog = repository.findAll();
        session.disableFilter("deletedProfileFilter");
        return dog;
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
        Profile profile = profileRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
        Breed breed = breedRepository.save(newDog.getBreed());
        Avatar avatar = avatarRepository.save(newDog.getAvatar());

        newJournal.setDog(newDog);
        newDog.setOwner(profile);
        newDog.setJournal(newJournal);
        newDog.setBreed(breed);
        newDog.setAvatar(avatar);

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

    @PutMapping("/{id}")
    public @ResponseBody Dog updateDog(@PathVariable Long id, @RequestBody Dog updates) {
        Dog updatedDog = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Breed breed = updates.getBreed();

        if (updates.getName() != null) updatedDog.setName(updates.getName());
        if (updates.getAge() != null) updatedDog.setAge(updates.getAge());
        if (updates.getWeight() != null) updatedDog.setWeight(updates.getWeight());
        if (updates.getSex() != null) updatedDog.setSex(updates.getSex());
        if (updates.getBreed() != null) updatedDog.setBreed(breed);
        if(updates.getJournal() != null) updatedDog.setJournal(updates.getJournal());

        breedRepository.save(breed);
//        journalRepository.save(updates.getJournal());

        return repository.save(updatedDog);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDog(@PathVariable Long id) {
        Dog deleteDog = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        deleteDog.setDeleted(true);
        repository.save(deleteDog);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
