package com.rajesh.poc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.rajesh.poc.dto.EmployeeDto;
import com.rajesh.poc.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
	
    EmployeeDto toEmployeeDto(Employee employee);
    Employee toEmployee(EmployeeDto employeeDto);


}
