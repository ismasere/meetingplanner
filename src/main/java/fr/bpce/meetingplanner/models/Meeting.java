
package fr.bpce.meetingplanner.models;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fr.bpce.meetingplanner.enumeration.MeetingType;
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
@Table(name = "meeting")
public class Meeting extends AbstractEntity {
	@NotNull(message = "The  the  meeting  name name must not be empty")
	@NotEmpty(message = "The  the  meeting  name name must not be empty")
	@NotBlank(message = "The  the  meeting  name name must not be empty")
	private String name;
	@NotNull(message = "The  the  meeting  startTime  must not be empty")
	private LocalDateTime startTime;
	@NotNull(message = "The  the  meeting  endTime  must not be empty")
	private LocalDateTime endTime;
	@NotNull(message = "The  the  meeting  attendees  must not be empty")
	private int attendees;
	@NotNull(message = "The  the  meeting  type  must not be empty")
	@Enumerated(EnumType.STRING)
	private MeetingType meetingType;

	@ManyToOne
	@NotNull(message = "The  the  meeting  room  must not be empty")
	private Room room;
@JsonManagedReference
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "meeting_reserved_equipments", joinColumns = @JoinColumn(name = "meeting_id"), inverseJoinColumns = @JoinColumn(name = "equipment_id"))
	private Set<Equipment> reservedEquipments;

}
