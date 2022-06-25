package com.careerdevs.conqureTheWalk.repositories;

import com.careerdevs.conqureTheWalk.models.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BreedRepository extends JpaRepository<Breed, Long> {
    Optional<Breed> findById(Long id);
}
