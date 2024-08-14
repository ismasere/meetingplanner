


package fr.bpce.meetingplanner.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.bpce.meetingplanner.models.Room;
import fr.bpce.meetingplanner.services.RoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 @author SERE
 @since 14 août 2024
**/
@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@Tag(name = "rooms")
public class RoomController {
	private final  RoomService roomService;
	@PostMapping("/")
	public ResponseEntity<Integer> save(@RequestBody Room Dto) {
		

		return ResponseEntity.ok(roomService.save(Dto));

	}
	
	@GetMapping("/")
	public ResponseEntity<List<Room>> findAll() {

		return ResponseEntity.ok(roomService.findAll());

	}
	@GetMapping("/{room-id}")
	public ResponseEntity<Optional<Room>> findById(@PathVariable("room-id") Integer roomId) {

		return ResponseEntity.ok(roomService.findById(roomId));

	}
	
	@DeleteMapping("/{room-id}")
	public ResponseEntity<Void> delete(@PathVariable("room-id") Integer roomId) {
		roomService.delete(roomId);
		return ResponseEntity.accepted().build();

	}
}

