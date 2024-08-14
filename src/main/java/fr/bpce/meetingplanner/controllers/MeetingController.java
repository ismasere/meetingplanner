


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

import fr.bpce.meetingplanner.dto.MeetingRequestDto;
import fr.bpce.meetingplanner.models.Meeting;
import fr.bpce.meetingplanner.services.MeetingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 @author SERE
 @since 14 août 2024
**/
@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
@Tag(name = "meetings")
public class MeetingController {
	
	private final MeetingService meetingService;

	    @PostMapping
	    public ResponseEntity<Meeting> scheduleMeeting(@RequestBody MeetingRequestDto meetingRequest) {
	        Meeting meeting = meetingService.scheduleMeeting(meetingRequest);
	        return ResponseEntity.ok(meeting);
	    }

	    @GetMapping("/day")
	    public ResponseEntity<List<Meeting>> getMeetingsForDay() {
	        List<Meeting> meetings = meetingService.getMeetingsForDay();
	        return ResponseEntity.ok(meetings);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> cancelMeeting(@PathVariable Integer id) {
	        meetingService.cancelMeeting(id);
	        return ResponseEntity.noContent().build();
	    }

}

