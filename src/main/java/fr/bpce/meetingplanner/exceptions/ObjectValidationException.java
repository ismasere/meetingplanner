/**
 @author SERE
 @since 14 août. 2024
**/


package fr.bpce.meetingplanner.exceptions;

import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException {
	  @Getter
	  private final Set<String> violations;

	  @Getter
	  private final String violationSource;

}
