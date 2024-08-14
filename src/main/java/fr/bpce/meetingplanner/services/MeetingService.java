


package fr.bpce.meetingplanner.services;

import java.time.LocalDateTime;
import java.util.List;

import fr.bpce.meetingplanner.dto.MeetingRequestDto;
import fr.bpce.meetingplanner.models.Meeting;
import fr.bpce.meetingplanner.models.Room;

/**
 @author SERE
 @since 14 août 2024
**/

public interface MeetingService {
    Meeting scheduleMeeting(MeetingRequestDto meetingRequest);
    List<Meeting> getMeetingsForDay();
    void cancelMeeting(Integer meetingId);
    List<Meeting> findByRoomAndStartTimeBetweenOrEndTimeBetween(Room room, LocalDateTime startTime , LocalDateTime endTime, LocalDateTime startTime2, LocalDateTime endTime2);
    void validateMeetingRequest(MeetingRequestDto meetingRequest);
    
}

