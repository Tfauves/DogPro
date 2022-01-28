package com.careerdevs.conqureTheWalk.repositories;

import com.careerdevs.conqureTheWalk.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DogRepository extends JpaRepository<Dog, Long> {

}
