package com.rajesh.poc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.poc.dto.EmployeeDto;
import com.rajesh.poc.exception.GlobalException;
import com.rajesh.poc.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/employees")
@Validated
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public ResponseEntity<Flux<EmployeeDto>> findAll() {
		return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/save")
	public Mono<ResponseEntity<EmployeeDto>> saveEmployee(@Valid @RequestBody EmployeeDto emp) {
		
		return employeeService.save(emp)
	              .map(employee -> ResponseEntity.ok(employee))
	              .doOnError(ex -> {
	            	    throw new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR,String.format("User is already exists with Emp Id(%s), please change to proceed", emp.getEmpId())
	            	    	      );
	            	    	   });
	}

	@GetMapping("/empId/{empId}")
	public Mono<ResponseEntity<EmployeeDto>> findByEmpId(@Valid @PathVariable Long empId) {
		
		return employeeService.findByEmpId(empId)
	              .map(employee -> ResponseEntity.ok(employee))
					.switchIfEmpty(Mono.error(new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR,String.format("Given Emp Id(%s) is not available, please change to proceed", empId))));
	}

	@GetMapping("/id/{id}")
	public Mono<ResponseEntity<EmployeeDto>> findById(@Valid @PathVariable String id) {
		
		return employeeService.findById(id)
	              .map(employee -> ResponseEntity.ok(employee))
					.switchIfEmpty(Mono.error(new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR,String.format("Given Id(%s) is not available, please change to proceed", id))));
	
	}

	@GetMapping(path = "/sal/{sal}")
	public ResponseEntity<Flux<EmployeeDto>> findBySal(@Valid @PathVariable Double sal) {
		return new ResponseEntity<>(employeeService.findBySalary(sal), HttpStatus.OK);
	}

	@PostMapping("/delete/{empId}")
	public Mono<ResponseEntity<String>> deleteByEmpId(@PathVariable Long empId) {
		
		return employeeService.deleteByEmpId(empId)
				.filter(deletedBy -> deletedBy == 1)
	              .map(employeeid -> ResponseEntity.ok(String.format("Employee Id (%s) successfully deleted", empId)))
					.switchIfEmpty(Mono.error(new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR,String.format("Employee ID (%s) not availabe, retry with new id", empId))));
		
	}
   
}

