package com.careerdevs.conqureTheWalk.controllers;

import com.careerdevs.conqureTheWalk.models.Activity;
import com.careerdevs.conqureTheWalk.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    @Autowired
    private ActivityRepository repository;

    @GetMapping
    public List<Activity> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody Activity newActivity) {
        return new ResponseEntity<>(repository.save(newActivity), HttpStatus.CREATED);
    }
}
