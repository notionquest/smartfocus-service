package com.smartfocus.test.exception;

import com.smartfocus.test.model.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GenericExceptionMapper extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {IllegalArgumentException.class})
	protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorMessage("Input arguments are not valid");
		errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
		return handleExceptionInternal(ex, errorMessage,
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = {UserNotFoundException.class})
	protected ResponseEntity<Object> userNotFound(RuntimeException ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorMessage("User not found");
		errorMessage.setErrorCode(HttpStatus.NO_CONTENT.value());
		return handleExceptionInternal(ex, errorMessage,
				new HttpHeaders(), HttpStatus.NO_CONTENT, request);
	}

	@ExceptionHandler(value = {Exception.class})
	protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorMessage("Server error");
		errorMessage.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return handleExceptionInternal(ex, errorMessage,
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}


}