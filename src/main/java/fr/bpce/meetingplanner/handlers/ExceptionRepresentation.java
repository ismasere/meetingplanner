/**
 @author SERE
 @since 14 Aout. 2024
**/


package fr.bpce.meetingplanner.handlers;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ExceptionRepresentation {
	  private String errorMessage;
	  private String errorSource;
	  private Set<String> validationErrors;


}
