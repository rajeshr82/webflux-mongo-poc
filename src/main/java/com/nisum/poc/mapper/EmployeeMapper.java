package com.nisum.poc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.nisum.poc.dto.EmployeeDto;
import com.nisum.poc.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
	
    EmployeeDto toEmployeeDto(Employee employee);
    Employee toEmployee(EmployeeDto employeeDto);


}
