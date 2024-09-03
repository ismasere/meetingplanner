


package fr.kata.meetingplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.kata.meetingplanner.models.Equipment;

/**
 @author SERE
 @since 14 août 2024
**/

public interface EquipmentRepository extends   JpaRepository<Equipment, Integer> {

}

