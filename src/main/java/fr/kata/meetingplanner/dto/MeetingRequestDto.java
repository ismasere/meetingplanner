


package fr.kata.meetingplanner.dto;

import java.time.LocalDateTime;

import fr.kata.meetingplanner.enumeration.MeetingType;
import lombok.Data;

/**
 @author SERE
 @since 14 août 2024
**/
@Data
public class MeetingRequestDto {
    private String name;

    private LocalDateTime startTime;
    
    private LocalDateTime endTime;
    private int attendees;
    private MeetingType meetingType;
}

