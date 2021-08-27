package com.nisum.poc.repository;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nisum.poc.entity.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public interface EmpMongoRepository extends ReactiveMongoRepository<Employee, String> {
	@Query(value="{emp_id : ?0}",sort = "{emp_id : 1}")
	public Mono<Employee> findByEmpId(Long empId);
	/**
	 * -1 -decending
	 * 1 ascending
	 * @param salary
	 * @return
	 */
	@Query(value="{emp_sal : {$gte: ?0}}",sort = "{emp_sal : -1}")
	public Flux<Employee> findBySalary(Double salary);
	
	@Query(value = "{emp_id : ?0}", delete = true)
	public Mono<Long> deleteByEmployeeId(Long empId); 
	
}
