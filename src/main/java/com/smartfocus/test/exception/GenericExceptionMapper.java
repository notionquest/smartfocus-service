package com.smartfocus.test.exception;

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
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Input arguments are not valid";
		return handleExceptionInternal(ex, bodyOfResponse,
				new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(value = {UserNotFoundException.class})
	protected ResponseEntity<Object> userNotFound(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "User not found";
		return handleExceptionInternal(ex, bodyOfResponse,
				new HttpHeaders(), HttpStatus.NO_CONTENT, request);
	}

}