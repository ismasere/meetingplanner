


package fr.kata.meetingplanner.services;

import java.util.List;

/**
 @author SERE
 @since 14 août 2024
**/

public interface AbstractService <T> {
	 Integer save(T dto);

	  List<T> findAll();

	  T findById(Integer id);

	  void delete(Integer id);
}

