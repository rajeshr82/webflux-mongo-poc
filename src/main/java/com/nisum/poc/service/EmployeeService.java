package com.nisum.poc.service;

import com.nisum.poc.dto.EmployeeDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

	public Mono<EmployeeDto> findByEmpId(Long empId);

	public Mono<EmployeeDto> findById(String id);

	public Flux<EmployeeDto> findAll();

	public Mono<EmployeeDto> save(EmployeeDto employeeDto);

	public Flux<EmployeeDto> findBySalary(Double sal);

	public Mono<Long> deleteByEmpId(Long empId);

}
