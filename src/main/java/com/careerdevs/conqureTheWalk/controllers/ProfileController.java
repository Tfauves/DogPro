package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.Profile;
import com.careerdevs.conqureTheWalk.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileRepository repository;

    @GetMapping
    public @ResponseBody
    List<Profile> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile newProfile) {
        return new ResponseEntity<>(repository.save(newProfile), HttpStatus.CREATED);
    }
}
