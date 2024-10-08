


package fr.kata.meetingplanner.services;

import java.util.List;
import java.util.Optional;

import fr.kata.meetingplanner.dto.AuthenticationRequest;
import fr.kata.meetingplanner.dto.AuthenticationResponse;
import fr.kata.meetingplanner.models.User;


/**
 @author SERE
 @since 14 août 2024
**/

public interface UserService {
	Optional<User> findByEmail(String email);
	   Integer save(User dto);
	  List<User> findAll();

	  User findById(Integer id);

	  void delete(Integer id);
	  AuthenticationResponse register(User user);

	  AuthenticationResponse authenticate(AuthenticationRequest request);

}

