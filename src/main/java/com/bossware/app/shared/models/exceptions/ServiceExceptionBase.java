package com.bossware.app.shared.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

public class ServiceExceptionBase extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6417981745418795713L;

	
	
	
	public ServiceExceptionBase(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}



}
