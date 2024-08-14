


package fr.bpce.meetingplanner.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bpce.meetingplanner.models.User;

/**
 @author SERE
 @since 14 août 2024
**/

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}

