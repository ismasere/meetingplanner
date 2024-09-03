
package fr.kata.meetingplanner.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.kata.meetingplanner.config.JwtUtils;
import fr.kata.meetingplanner.dto.AuthenticationRequest;
import fr.kata.meetingplanner.dto.AuthenticationResponse;
import fr.kata.meetingplanner.models.Role;
import fr.kata.meetingplanner.models.User;
import fr.kata.meetingplanner.repositories.RoleRepository;
import fr.kata.meetingplanner.repositories.UserRepository;
import fr.kata.meetingplanner.services.UserService;
import lombok.RequiredArgsConstructor;

/**
 * @author SERE
 * @since 14 août 2024
 **/
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authManager;
	private final RoleRepository roleRepository;
	private static final String ROLE_USER = "ROLE_USER";

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer save(User dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return userRepository.save(dto).getId();
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Integer id) {
		return userRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided ID : " + id));
	}

	@Override
	public void delete(Integer id) {
		userRepository.deleteById(id);

	}

	@Override
	public AuthenticationResponse register(User user) {
		    user.setPassword(passwordEncoder.encode(user.getPassword()));
		    user.setActive(true);
		  //  user.setActive(true);
		    if(user.getRole()!=null){
		    	Optional<Role> roleToset =roleRepository.findById(user.getRole().getId());
		    	 if(roleToset.isPresent()) {
		    		 user.setRole(roleToset.get());
	
			    	
		    	     }
		    	 
		    	 }else {	
		    	    	  user.setRole(
		    	    		        findOrCreateRole(ROLE_USER)
		    	    		    );
		    	    	 
		    		 	
		     }
		    var savedUser = userRepository.save(user);
		    Map<String, Object> claims = new HashMap<>();
		    claims.put("userId", savedUser.getId());
		    claims.put("fullName", savedUser.getFirstname() + " " + savedUser.getLastname());
		    String token = jwtUtils.generateToken(savedUser, claims);
		    return AuthenticationResponse.builder()
		        .token(token)
		        .build();
	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		
	    authManager.authenticate(
	        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
	    );

	    final User user = userRepository.findByEmail(request.getEmail()).get();
	    Map<String, Object> claims = new HashMap<>();
	    claims.put("userId", user.getId());
	    claims.put("fullName", user.getFirstname() + " " + user.getLastname());
	    final String token = jwtUtils.generateToken(user, claims);
	    return AuthenticationResponse.builder()
	        .token(token)
	        .build();
	}
	
	
	  private Role findOrCreateRole(String roleName) {
		    Role role = roleRepository.findByName(roleName)
		        .orElse(null);
		    if (role == null) {
		      return roleRepository.save(
		          Role.builder()
		              .name(roleName)
		              .build()
		      );
		    }
		    return role;
		  }

}
