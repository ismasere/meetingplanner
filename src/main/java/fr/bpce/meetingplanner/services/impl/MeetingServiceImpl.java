
package fr.bpce.meetingplanner.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.stereotype.Service;

import fr.bpce.meetingplanner.dto.MeetingRequestDto;
import fr.bpce.meetingplanner.enumeration.MeetingType;
import fr.bpce.meetingplanner.models.Equipment;
import fr.bpce.meetingplanner.models.Meeting;
import fr.bpce.meetingplanner.models.Room;
import fr.bpce.meetingplanner.repositories.EquipmentRepository;
import fr.bpce.meetingplanner.repositories.MeetingRepository;
import fr.bpce.meetingplanner.repositories.RoomRepository;
import fr.bpce.meetingplanner.services.MeetingService;
import lombok.RequiredArgsConstructor;

/**
 * @author SERE
 * @since 14 août 2024
 **/
@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

	private final MeetingRepository meetingRepository;

	private final RoomRepository roomRepository;

	private final EquipmentRepository equipmentRepository;
	private static final String ECRAN = "Écran";
	private static final String PIEUVRE = "Pieuvre";
	private static final String WEBCAM = "Webcam";
	private static final String TAB = "Tableau";
	// meeting for a day must be scheduled between 8 a.m. and 8 p.m
	private static final String STARTDATE = "08:00:00Z";
	private static final String ENDATE = "20:00:00Z";
	private static final String PLUS = "+";
	private static final String TSYMBOL = "T";

	@Override
	public Meeting scheduleMeeting(MeetingRequestDto meetingRequest) {
		// Validate the meeting request
		validateMeetingRequest(meetingRequest);

		// Find an available room
		Room availableRoom = findAvailableRoom(meetingRequest);

		if (availableRoom == null) {
			throw new RuntimeException("No room available for the requested slot.");
		}

		// Create the meeting
		Meeting meeting = new Meeting();
		meeting.setName(meetingRequest.getName());
		meeting.setStartTime(meetingRequest.getStartTime());
		meeting.setEndTime(meetingRequest.getEndTime());
		meeting.setAttendees(meetingRequest.getAttendees());
		meeting.setMeetingType(meetingRequest.getMeetingType());
		meeting.setRoom(availableRoom);
		// Reserve the room and necessary equipment
		Set<Equipment> reserved = reserveEquipmentAndRoom(meetingRequest, availableRoom);
		meeting.setReservedEquipments(reserved);
		// Save the meeting
		return meetingRepository.save(meeting);
	}

	@Override
	public List<Meeting> getMeetingsForDay() {
		LocalDateTime ldt = LocalDateTime.now();
		StringBuilder start = new StringBuilder();
		StringBuilder end = new StringBuilder();
		start.append(PLUS);
		start.append(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt));
		start.append(TSYMBOL).append(STARTDATE);
		end.append(PLUS);
		end.append(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt));
		end.append(TSYMBOL).append(ENDATE);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("+yyyy-MM-dd'T'HH:mm:ss'Z'")
				.withZone(ZoneId.of("UTC"));

		LocalDateTime dateTimeStart = LocalDateTime.parse(start.toString(), formatter);
		LocalDateTime dateTimeEnd = LocalDateTime.parse(end.toString(), formatter);

		return meetingRepository.findByStartTimeBetween(dateTimeStart, dateTimeEnd);
	}

	@Override
	public void cancelMeeting(Integer meetingId) {
		Meeting meeting = meetingRepository.findById(meetingId)
				.orElseThrow(() -> new RuntimeException("Meeting not found"));

		// Release reserved equipment
		releaseEquipment(meeting);

		// Delete meeting
		meetingRepository.deleteById(meetingId);
	}

	@Override
	public void validateMeetingRequest(MeetingRequestDto meetingRequest) {
		if (meetingRequest.getStartTime().getHour() < 8 || meetingRequest.getEndTime().getHour() > 20) {
			throw new RuntimeException("Meetings can only be scheduled between 8 a.m. and 8 p.m.");
		}

		if (meetingRequest.getAttendees() > 23) {
			throw new RuntimeException("No room can accommodate more than 23 participants.");
		}

		// Checking the cleaning interval
		LocalDateTime cleaningTime = meetingRequest.getStartTime().minusHours(1);
		if (!meetingRepository.findConflictingMeetings(cleaningTime, meetingRequest.getEndTime()).isEmpty()) {
			throw new RuntimeException("A room must be free 1 hour before another reservation.");
		}
	}

	/**
	 * 
	 * @param meetingRequest
	 * @return
	 */
	private Room findAvailableRoom(MeetingRequestDto meetingRequest) {
		List<Room> rooms = roomRepository.findAll();

		for (Room room : rooms) {
			if (isRoomSuitable(room, meetingRequest) && isRoomAvailable(room, meetingRequest)) {
				return room;
			}
		}

		return null;
	}

	/**
	 * 
	 * @param room
	 * @param meetingRequest
	 * @return
	 */
	private boolean isRoomSuitable(Room room, MeetingRequestDto meetingRequest) {
		if (room.getCapacity() < meetingRequest.getAttendees()) {
			return false;
		}

		// Checking the necessary equipment
		Set<Equipment> requiredEquipments = getRequiredEquipment(meetingRequest.getMeetingType());
		for (Equipment equipment : requiredEquipments) {
			if (!room.getEquipments().contains(equipment) && !equipment.isAvailable()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 
	 * @param room
	 * @param meetingRequest
	 * @return
	 */
	private boolean isRoomAvailable(Room room, MeetingRequestDto meetingRequest) {
		List<Meeting> meetings = meetingRepository.findByRoomAndTimeRange(room.getId(), meetingRequest.getStartTime(),
				meetingRequest.getEndTime());

		return meetings.isEmpty();
	}

	/***
	 * 
	 * @param meetingType
	 * @return
	 */
	private Set<Equipment> getRequiredEquipment(MeetingType meetingType) {
		Set<Equipment> requiredEquipments = new HashSet<>();

		switch (meetingType) {
		case VC:
			requiredEquipments.add(new Equipment(ECRAN, true, null));
			requiredEquipments.add(new Equipment(PIEUVRE, true, null));
			requiredEquipments.add(new Equipment(WEBCAM, true, null));
			break;
		case SPEC:
			requiredEquipments.add(new Equipment(TAB, true, null));
			break;
		case RC:
			requiredEquipments.add(new Equipment(ECRAN, true, null));
			requiredEquipments.add(new Equipment(PIEUVRE, true, null));
			requiredEquipments.add(new Equipment(TAB, true, null));
			break;
		case RS:
			break;
		}

		return requiredEquipments;
	}

	/**
	 * 
	 * @param meetingRequest
	 * @param room
	 * @return
	 */
	private Set<Equipment> reserveEquipmentAndRoom(MeetingRequestDto meetingRequest, Room room) {
		Set<Equipment> requiredEquipments = getRequiredEquipment(meetingRequest.getMeetingType());
		Set<Equipment> equipmentServed = new HashSet<Equipment>();
		for (Equipment equipment : requiredEquipments) {
			if (!room.getEquipments().contains(equipment)) {
				equipment.setAvailable(false);
				equipmentServed.add(equipment);

			}
		}
		return equipmentServed;
	}

	/**
	 * 
	 * @param meeting
	 */
	private void releaseEquipment(Meeting meeting) {
		for (Equipment equipment : meeting.getReservedEquipments()) {
			equipment.setAvailable(true);
			equipmentRepository.save(equipment);
		}
	}

	@Override
	public List<Meeting> findByRoomAndStartTimeBetweenOrEndTimeBetween(Room room, LocalDateTime startTime,
			LocalDateTime startTimeEnd, LocalDateTime endTimeStart, LocalDateTime endTimeEnd) {
		return meetingRepository.findByRoomAndStartTimeBetweenOrEndTimeBetween(room.getId(), startTime, startTimeEnd,
				endTimeStart, endTimeEnd);
	}

}
