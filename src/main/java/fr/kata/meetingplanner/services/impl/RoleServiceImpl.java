


package fr.kata.meetingplanner.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import fr.kata.meetingplanner.models.Role;
import fr.kata.meetingplanner.repositories.RoleRepository;
import fr.kata.meetingplanner.services.RoleService;
import lombok.RequiredArgsConstructor;

/**
 @author SERE
 @since 14 août 2024
**/
@Service
@RequiredArgsConstructor
public class RoleServiceImpl  implements RoleService {
	private final RoleRepository roleRepository;
	@Override
	public Integer save(Role dto) {
	  return	roleRepository.save(dto).getId();
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Integer id) {
		return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No role found with the ID : " + id));
	}

	@Override
	public void delete(Integer id) {
		roleRepository.deleteById(id);
		
	}

}

