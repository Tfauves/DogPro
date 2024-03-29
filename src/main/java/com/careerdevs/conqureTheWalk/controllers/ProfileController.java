package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.*;
import com.careerdevs.conqureTheWalk.models.auth.User;
import com.careerdevs.conqureTheWalk.repositories.*;
import com.careerdevs.conqureTheWalk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Objects;


@CrossOrigin
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileRepository repository;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    BreedRepository breedRepository;

    @Autowired
    AvatarRepository avatarRepository;

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public @ResponseBody Profile getById() {
        User currentUser = userService.getCurrentUser();

        if(currentUser == null) {
            return null;
        }

        return repository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/self")
    public @ResponseBody Profile getSelf() {
        User currentUser = userService.getCurrentUser();

        if(currentUser == null) {
            return null;
        }
        return repository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Profile> createBasicProfile(@RequestBody Profile newProfile) {
        User currentUser = userService.getCurrentUser();
        if(currentUser == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Avatar avatar = new Avatar();
        newProfile.setAvatar(avatar);
        avatarRepository.save(avatar);
        newProfile.setUser(currentUser);
        return new ResponseEntity<>(repository.save(newProfile), HttpStatus.CREATED);
    }

    @PutMapping()
    public Profile updateProfile(@RequestBody Profile pro) {

        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }

        Profile profile = repository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!Objects.equals(pro.getName(), "")) profile.setName(pro.getName());
        if (!Objects.equals(pro.getAvatar().getUrl(), "")) {
            Avatar avatar = pro.getAvatar();
            avatar.setUrl(pro.getAvatar().getUrl());
            avatarRepository.save(avatar);
            profile.setAvatar(avatar);

        }
        Avatar avatar = profile.getAvatar();
        avatarRepository.save(avatar);

        return repository.save(profile);

    }

    @DeleteMapping
    public ResponseEntity<String> destroyProfile() {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        repository.deleteByUser_id(currentUser.getId());
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


    ////////////////////////////Admin Routes///////////////////////////////
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    List<Profile> getAll() {
        return repository.findAll();
    }



    @PutMapping("/dog/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Profile addDogToProfileById(@RequestBody List<Dog> addDog, @PathVariable Long id) {

        Profile profile = repository.findByUser_id(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        for (Dog dog : addDog) {
            dog.setOwner(profile);
            dogRepository.save(dog);
        }
        profile.getMyDogs().addAll(addDog);
        return repository.save(profile);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> destroyDevById(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
