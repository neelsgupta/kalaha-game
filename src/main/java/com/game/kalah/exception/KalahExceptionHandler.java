package com.game.kalah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class KalahExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { InvalidIdException.class })
	protected ResponseEntity<ErrorMessage> handleConflict(InvalidIdException ex) {
		ErrorMessage em = new ErrorMessage();
		em.setId(ex.getId());
		em.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(em, HttpStatus.BAD_REQUEST);
	}
}

 class ErrorMessage{
	
	 private String id;
	 
	 private String message;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	 
}
