package com.nisum.poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import reactor.core.publisher.Mono;

@ControllerAdvice
public class HandleExceptions {
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = EmployeeNotFoundException.class)
   public ResponseEntity<String> handleEmp(EmployeeNotFoundException e){
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = EmployeeAlreadyExistsException.class)
    public ResponseEntity<String> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException e) {
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(e.getMessage());

    	//return Mono.just(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = Exception.class)
    public Mono<String> handleAllExceptions(Exception e) {
        return Mono.just(e.getMessage());
    }
}
