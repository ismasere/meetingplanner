


package fr.bpce.meetingplanner.services;

import java.util.List;

import fr.bpce.meetingplanner.models.Role;

/**
 @author SERE
 @since 14 août 2024
**/

public interface RoleService {
	   Integer save(Role dto);
	  List<Role> findAll();

	  Role findById(Integer id);

	  void delete(Integer id);

}

