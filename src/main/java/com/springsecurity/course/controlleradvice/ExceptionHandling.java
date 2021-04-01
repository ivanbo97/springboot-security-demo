package com.springsecurity.course.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springsecurity.course.errorresponse.StudentErrorResponse;
import com.springsecurity.course.exception.StudentNotFoundException;

@ControllerAdvice
public class ExceptionHandling {

 @ExceptionHandler(StudentNotFoundException.class)
 public ResponseEntity<StudentErrorResponse> handlerStudentNotFound(StudentNotFoundException exc){
	 
	 StudentErrorResponse errorResponse = new StudentErrorResponse();
	 errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
	 errorResponse.setMessage(exc.getMessage());
	 errorResponse.setTimestamp(System.currentTimeMillis());
	 
	 return new ResponseEntity<StudentErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
 }
}
