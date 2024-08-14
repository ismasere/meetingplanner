


package fr.bpce.meetingplanner.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 @author SERE
 @since 14 août 2024
**/
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Equipment")
public class Equipment extends AbstractEntity {
	@NotNull(message = "The  the  equipment  name name must not be empty")
	@NotEmpty(message = "The  the  equipment  name name must not be empty")
	@NotBlank(message = "The  the  equipment  name name must not be empty")
	private String name;
    private boolean isAvailable;
    @ManyToMany(mappedBy = "reservedEquipments",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Meeting> meetings;
    
}

