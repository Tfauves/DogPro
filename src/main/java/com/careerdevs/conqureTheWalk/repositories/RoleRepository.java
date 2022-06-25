package com.careerdevs.conqureTheWalk.repositories;

import com.careerdevs.conqureTheWalk.models.auth.ERole;
import com.careerdevs.conqureTheWalk.models.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
