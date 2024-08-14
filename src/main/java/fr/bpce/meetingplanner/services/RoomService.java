
package fr.bpce.meetingplanner.services;

import java.util.List;
import java.util.Optional;

import fr.bpce.meetingplanner.models.Room;

/**
 * @author SERE
 * @since 14 août 2024
 **/

public interface RoomService {
	List<Room> findAll();
	Integer save(Room dto);
	Optional<Room> findById(Integer id);
	void delete(Integer id);

}
