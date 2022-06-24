package com.careerdevs.conqureTheWalk.repositories;

import com.careerdevs.conqureTheWalk.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DogRepository extends JpaRepository<Dog, Long> {

}
