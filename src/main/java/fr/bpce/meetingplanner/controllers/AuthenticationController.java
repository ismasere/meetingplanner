package fr.bpce.meetingplanner.controllers;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.bpce.meetingplanner.dto.AuthenticationRequest;
import fr.bpce.meetingplanner.dto.AuthenticationResponse;
import fr.bpce.meetingplanner.models.User;
import fr.bpce.meetingplanner.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
@author SERE
@since 14 août. 2024
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "authentication")
public class AuthenticationController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody User user) {

    return ResponseEntity.ok(userService.register(user));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(userService.authenticate(request));
  }

}
