package com.careerdevs.conqureTheWalk.controllers;


import com.careerdevs.conqureTheWalk.payload.response.api.response.BreedResponse;
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
//    https://api.thedogapi.com/v1/breeds/search?q=gold&api_key=7fada790-629b-4568-b94b-26d57a03e22f



    @GetMapping("/breed/{q}")
   public ResponseEntity<?> getDogBreedInfo(@PathVariable String q) {
   String uri = "https://api.thedogapi.com/v1/breeds/search" + "?q=" + q + "&api_key=" + apiKey;


        BreedResponse response = restTemplate.getForObject(uri, BreedResponse.class);
        return ResponseEntity.ok(response.getBreedInfoList());
    }
}
