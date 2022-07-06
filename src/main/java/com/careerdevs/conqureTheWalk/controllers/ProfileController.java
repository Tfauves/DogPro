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
        newProfile.setUser(currentUser);
        return new ResponseEntity<>(repository.save(newProfile), HttpStatus.CREATED);
    }

    @PutMapping("/photo")
    public Profile addPhoto(@RequestBody Profile pro) {

        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }

        Profile profile = repository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (profile.getAvatar() != null) {
            Avatar avatar = profile.getAvatar();
            avatar.setUrl(pro.getAvatar().getUrl());
            avatarRepository.save(avatar);
            return profile;
        }
        Avatar avatar = avatarRepository.save(pro.getAvatar());
        profile.setAvatar(avatar);
        return repository.save(profile);

    }
    // original create profile mapping, separated into two mappings
//    @PostMapping
//    public ResponseEntity<Profile> createProfile(@RequestBody Profile newProfile) {
//        User currentUser = userService.getCurrentUser();
//
//        if (currentUser == null) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//        newProfile.setUser(currentUser);
//
//        if (newProfile.getAvatar() != null) {
//            Avatar avatar = newProfile.getAvatar();
//            avatar.setUrl(newProfile.getAvatar().getUrl());
//            avatarRepository.save(avatar);
//        }
//        Avatar avatar = avatarRepository.save(newProfile.getAvatar());
//        newProfile.setAvatar(avatar);
//
//        return new ResponseEntity<>(repository.save(newProfile), HttpStatus.CREATED);
//    }

    @PutMapping
    public @ResponseBody Profile updateProfile(@RequestBody Profile updateData) {

        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return null;
        }
        Profile profile = repository.findByUser_id(currentUser.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updateData.getName() != null) profile.setName(updateData.getName());
        if (updateData.getAvatar() != null) {
            Avatar avatar = profile.getAvatar();
            avatar.setUrl(updateData.getAvatar().getUrl());
            avatarRepository.save(avatar);
        }
        Avatar avatar = avatarRepository.save(updateData.getAvatar());
        profile.setAvatar(avatar);
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
