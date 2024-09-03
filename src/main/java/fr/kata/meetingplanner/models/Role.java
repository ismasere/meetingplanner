


package fr.kata.meetingplanner.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@Table(name = "user_role")
public class Role extends AbstractEntity{
	@NotNull(message = "The  name must not be empty")
	@NotEmpty(message = "The  name must not be empty")
	@NotBlank(message = "Le nom ne doit pas être vide")
	private String name;
	@OneToMany(mappedBy = "role")
	private Set<User> users;

}

