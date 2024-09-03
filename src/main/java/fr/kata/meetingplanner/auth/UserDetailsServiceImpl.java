/**
 @author SERE
 @since 14 août. 2024
**/


package fr.kata.meetingplanner.auth;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.kata.meetingplanner.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserRepository repository;

	  @Override
	  @Transactional
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    return repository.findByEmail(email)
	        .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided email"));
	  }

}
