package com.careerdevs.conqureTheWalk.repositories;

import com.careerdevs.conqureTheWalk.models.Journal;
import com.careerdevs.conqureTheWalk.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {


}
