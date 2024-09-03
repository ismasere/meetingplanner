


package fr.kata.meetingplanner.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.kata.meetingplanner.models.Room;
import fr.kata.meetingplanner.repositories.RoomRepository;
import fr.kata.meetingplanner.services.RoomService;
import lombok.RequiredArgsConstructor;

/**
 @author SERE
 @since 14 août 2024
**/
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
	private final RoomRepository roomRepository;
	@Override
	public List<Room> findAll() {
		 return roomRepository.findAll();
	}
	@Override
	public Integer save(Room dto) {
		return roomRepository.save(dto).getId();
	}
	@Override
	public Optional<Room> findById(Integer id) {
		return roomRepository.findById(id);
	}
	@Override
	public void delete(Integer id) {
		roomRepository.deleteById(id);
		
	}

	
	

	
	

}

