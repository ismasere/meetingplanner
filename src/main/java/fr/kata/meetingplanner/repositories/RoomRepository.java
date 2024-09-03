


package fr.kata.meetingplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.kata.meetingplanner.models.Room;

/**
 @author SERE
 @since 14 août 2024
**/

public interface RoomRepository extends JpaRepository<Room, Integer> {

}

