


package fr.bpce.meetingplanner.models;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@Table(name = "b_user")
public class User  extends AbstractEntity implements UserDetails{
	@NotNull(message = "The  firstname must not be empty")
	@NotEmpty(message = "The  firstname must not be empty")
	@NotBlank(message = "The  firstname must not be empty")
	  private String firstname;
	@NotNull(message = "The  lastname must not be empty")
	@NotEmpty(message = "The  lastname must not be empty")
	@NotBlank(message = "LThe  lastname must not be empty")
	  private String lastname;
	@NotNull(message = "The email must not be empty")
	@NotEmpty(message = "L'email nmust not be empty")
	@NotBlank(message = "L'email must not be empty")
	@Email(message = "This email is invalid")
	  @Column(unique = true)
	  private String email;
	@NotNull(message = "The password  must not be empty")
	@NotEmpty(message = "The password  must not be empty")
	@NotBlank(message = "The password  must not be empty")
	  private String password;

	  private boolean active;

	  @OneToOne
	  @NotNull(message = "The  role must not be empty")
	  private Role role;
	  


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
	}


	@Override
	public String getUsername() {

		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {

		return active;
	}

}


