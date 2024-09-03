/**
 @author SERE
 @since 14 août. 2024
**/


package fr.kata.meetingplanner.handlers;

import java.sql.SQLNonTransientConnectionException;
import java.util.Locale;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.kata.meetingplanner.exceptions.ObjectValidationException;




@RestControllerAdvice

public class GlobalExceptionHandler {
	@Autowired
	  private MessageSource messageSource;
	  @ExceptionHandler({ObjectValidationException.class})
	  public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception) {
	    ExceptionRepresentation representation = ExceptionRepresentation.builder()
	        .errorMessage("Object not valid exception has occurred")
	        .errorSource(exception.getViolationSource())
	        .validationErrors(exception.getViolations())
	        .build();
	    return ResponseEntity
	        .status(HttpStatus.BAD_REQUEST)
	        .contentType(MediaType.APPLICATION_JSON)
	        .body(representation);
	  }
	  @ExceptionHandler(EntityNotFoundException.class)
	  public ResponseEntity<ExceptionRepresentation> handleException(EntityNotFoundException exception) {
	    ExceptionRepresentation representation = ExceptionRepresentation.builder()
	        .errorMessage(exception.getMessage())
	        .build();
	    return ResponseEntity
	        .status(HttpStatus.NOT_FOUND)
	        .body(representation);
	  }
	  
	  
	  @ExceptionHandler(DataIntegrityViolationException.class)
	  public ResponseEntity<ExceptionRepresentation> handleException() {
	    ExceptionRepresentation representation = ExceptionRepresentation.builder()
//	        .errorMessage("A user already exists with the provided Email or you try to delete/update linked data")
	        
	        .errorMessage(getLocalizedMessage("exception.user.already.exist"))
	        .build();
	    return ResponseEntity
	        .status(HttpStatus.BAD_REQUEST)
	        .body(representation);
	  }
	  
	  @ExceptionHandler(SQLNonTransientConnectionException.class)
	  public ResponseEntity<ExceptionRepresentation> handleException(SQLNonTransientConnectionException exception) {
	    ExceptionRepresentation representation = ExceptionRepresentation.builder()
	        .errorMessage(getLocalizedMessage("exception.user.already.exist"))
	        .build();
	    return ResponseEntity
	        .status(HttpStatus.BAD_REQUEST)
	        .body(representation);
	  }
	  
	  
	  
	  
	  
	  
	  @ExceptionHandler(BadCredentialsException.class)
	  public ResponseEntity<ExceptionRepresentation> handleBadCredentialsException() {
	    ExceptionRepresentation representation = ExceptionRepresentation.builder()
	        .errorMessage(getLocalizedMessage("exception.pssword.incorrect"))
	        .build();
	    return ResponseEntity
	        .status(HttpStatus.FORBIDDEN)
	        .body(representation);
	  }
	  
  
	  
	  private String getLocalizedMessage(String translationKey)
	  {
	    Locale locale = LocaleContextHolder.getLocale();
	    return messageSource.getMessage(translationKey, null, locale);
	  }
	  
	  
}
