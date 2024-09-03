


package fr.kata.meetingplanner.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.kata.meetingplanner.models.User;

/**
 @author SERE
 @since 14 août 2024
**/

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}

