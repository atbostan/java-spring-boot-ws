package com.bossware.app.shared.models.exceptions;

import java.util.Date;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AppExceptionHandler {
	//By doing specified a class inside of annotation this is valid only one user service ex.
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleExceptions(ServiceExceptionBase ex,WebRequest req){
		
		ErrorMessageModel errorMessage = new ErrorMessageModel(new Date(), ex.getMessage(), ex.getClass().toString());
		return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		
		// Sample Output Without Error Message Class-> Missing required field. Please check documentation for required fields
	}
}
