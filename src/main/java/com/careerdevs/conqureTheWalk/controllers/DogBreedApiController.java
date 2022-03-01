package com.careerdevs.conqureTheWalk.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
@RequestMapping("/api/info")
public class DogBreedApiController {
    @Autowired
    RestTemplate restTemplate;

    @Value("${ConquerTheWalk.app.dogBreedApiKey}")
    private String apiKey;

//    @GetMapping("/breed/{q}")
//    public ResponseEntity<?> getDogBreedInfo(@PathVariable String q) {
//    String uri = "https://api.thedogapi.com/v1/breeds/search" + "?q=" + q;
//        return ResponseEntity.ok();
//    }
}
