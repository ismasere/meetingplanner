
package fr.kata.meetingplanner.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author SERE
 * @since 14 août 2024
 **/

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Room")
public class Room  extends AbstractEntity{
	@NotNull(message = "The  the  room  name name must not be empty")
	@NotEmpty(message = "The  the  room  name name must not be empty")
	@NotBlank(message = "The  the  room  name name must not be empty")
	private String name;
	@NotNull(message = "The  the  room  name name must not be empty")
	private int capacity;
	@ManyToMany
	@JoinTable(name = "room_equipments", 
	joinColumns = @JoinColumn(name = "room_id"),
	inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Set<Equipment> equipments;

}
