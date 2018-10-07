/**
 * 
 */
package com.fouad.toyrobot.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fouad.toyrobot.exception.InvalidCommandException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Ahmed
 *
 *         An aggregated error handler class
 */
@ControllerAdvice
@Slf4j
public class ExceptionsHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<HttpStatus> handleError404(NoHandlerFoundException ex) {
		log.debug("Resource not found exception occurred! ", ex);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<HttpStatus> handleThrowable(Throwable ex) {
		log.debug("Unhandled exception occurred! ", ex);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<HttpStatus> handleGeneralException(Exception ex) {
		log.debug("General exception occurred! ", ex);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidCommandException.class)
	public ResponseEntity<HttpStatus> handleInvalidCommandException(InvalidCommandException ex) {
		log.debug(ex.getMessage(), ex);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
