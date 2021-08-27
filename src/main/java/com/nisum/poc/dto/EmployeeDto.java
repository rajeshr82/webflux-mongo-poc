package com.nisum.poc.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	@NotNull(message = "empID should not be null")
	private Long empId;
	@NotNull(message = "empName should not be null")
	private String empName;
	@NotNull(message = "empSal should not be null")
	private Double empSal;
	@NotNull(message = "deptId should not be null")
	private String deptId;
	@NotNull(message = "deptName should not be null")
	private String deptName;


}
