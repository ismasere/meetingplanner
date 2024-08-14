
package fr.bpce.meetingplanner.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.bpce.meetingplanner.models.User;
import fr.bpce.meetingplanner.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
/**
@author SERE
@since 14 août. 2024
**/

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "user")
public class UserController {

	private final UserService userService;

	@PostMapping("/")
	public ResponseEntity<Integer> save(@RequestBody User userDto) {
		return ResponseEntity.ok(userService.save(userDto));
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{user-id}")
	public ResponseEntity<User> findById(@PathVariable("user-id") Integer userId) {
		return ResponseEntity.ok(userService.findById(userId));
	}

	@DeleteMapping("/{user-id}")
	public ResponseEntity<Void> delete(@PathVariable("user-id") Integer userId) {
		userService.delete(userId);
		return ResponseEntity.accepted().build();
	}
	


}
