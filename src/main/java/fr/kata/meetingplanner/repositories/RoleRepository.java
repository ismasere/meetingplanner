


package fr.kata.meetingplanner.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.kata.meetingplanner.models.Role;

/**
 @author SERE
 @since 14 août 2024
**/

public interface RoleRepository  extends  JpaRepository<Role, Integer> {
	Optional<Role> findByName(String role);
}

