package com.careerdevs.conqureTheWalk.repositories;

import com.careerdevs.conqureTheWalk.models.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedController extends JpaRepository<Breed, Long> {
}
