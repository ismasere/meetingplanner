


package fr.kata.meetingplanner.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.kata.meetingplanner.models.Meeting;

/**
 @author SERE
 @since 14 août 2024
**/

public interface MeetingRepository  extends   JpaRepository<Meeting, Integer>{

	
	@Query(value="SELECT * FROM Meeting\n"
			+ "	WHERE room_id = :roomId\n"
			+ "	AND (\n"
			+ "	    (start_time BETWEEN :startTimeStart AND :startTimeEnd)\n"
			+ "	    OR\n"
			+ "	    (end_time BETWEEN :endTimeStart AND :endTimeEnd)\n"
			+ "	)",nativeQuery = true)
	List<Meeting> findByRoomAndStartTimeBetweenOrEndTimeBetween(Integer roomId,LocalDateTime startTimeStart,LocalDateTime startTimeEnd,LocalDateTime endTimeStart,LocalDateTime endTimeEnd);

	
	
	@Query(value="SELECT * FROM Meeting\n"
			+ "	WHERE room_id = :roomId\n"
			+ "	AND (\n"
			+ "	    (start_time BETWEEN :startime AND :endTime)\n"
			+ "	)",nativeQuery = true)
	List<Meeting> findByRoomAndTimeRange(Integer roomId,LocalDateTime startime,LocalDateTime endTime);

	
	@Query(value="SELECT * FROM Meeting\n"
			+ "	WHERE start_time BETWEEN :startime AND :endTime 	",nativeQuery = true)
	List<Meeting> findConflictingMeetings(LocalDateTime startime,LocalDateTime endTime);
	
	
	@Query(value="SELECT * FROM Meeting\n"
			+ "	WHERE start_time BETWEEN :startime AND :endTime \n ",nativeQuery = true)
	List<Meeting> findByStartTimeBetween(LocalDateTime startime,LocalDateTime endTime);
	
	
	
	
	
	
	
	
}

