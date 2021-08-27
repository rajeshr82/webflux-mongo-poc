package com.nisum.poc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.nisum.poc.dto.EmployeeDto;
import com.nisum.poc.entity.Employee;
import com.nisum.poc.mapper.EmployeeMapper;
import com.nisum.poc.repository.EmpMongoRepository;
import com.nisum.poc.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmpMongoRepository empMongoRepository;
	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;

	@Override
	public Mono<EmployeeDto> findByEmpId(Long empId) {
		Mono<Employee> monoEmployee = empMongoRepository.findByEmpId(empId);
		Mono<EmployeeDto> monoEmp = monoEmployee.map(e -> employeeMapper.toEmployeeDto(e));
		return monoEmp;
	}

	@Override
	public Mono<EmployeeDto> findById(String id) {
		Mono<Employee> monoEmployee = empMongoRepository.findById(id);
		Mono<EmployeeDto> monoEmp = monoEmployee.map(e -> employeeMapper.toEmployeeDto(e)).map(e -> (EmployeeDto) e);
		return monoEmp;
	}

	@Override
	public Flux<EmployeeDto> findAll() {
		Flux<Employee> list = empMongoRepository.findAll();
		Flux<EmployeeDto> empList = list.map(e -> employeeMapper.toEmployeeDto(e)).map(e -> (EmployeeDto) e);

		return empList;

	}

	@Override
	public Mono<EmployeeDto> save(EmployeeDto employeeDto) {
		
		Employee employee = employeeMapper.toEmployee(employeeDto);
		return empMongoRepository.save(employee)
				.map(e -> {
					EmployeeDto employeeDto1 = employeeMapper.toEmployeeDto(e);
					kafkaTemplate.send("employeetopic","User Created with ID:: "+employeeDto1.getEmpId());
					kafkaTemplate.send("employee",employeeDto1);
					return employeeDto1;
				}).map(e -> (EmployeeDto) e);
	}

	@Override
	public Flux<EmployeeDto> findBySalary(Double sal) {
		Flux<Employee> stream = empMongoRepository.findBySalary(sal);
		Flux<EmployeeDto> empList = stream.map(e -> employeeMapper.toEmployeeDto(e)).map(e -> (EmployeeDto) e);
		return empList;
	}

	@Override
	public Mono<Long> deleteByEmpId(Long empId) {
		return empMongoRepository.deleteByEmployeeId(empId);
	}

}
