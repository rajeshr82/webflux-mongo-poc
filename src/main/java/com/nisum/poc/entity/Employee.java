package com.nisum.poc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "employee")
public class Employee {
	@Id
	private String _id;
	@Field(name = "emp_id")
	@Indexed(unique=true)
	private Long empId;
	@Field(name = "emp_name")
	private String empName;
	@Field(name = "emp_sal")
	private Double empSal;
	@Field(name = "dept_id")
	private String deptId;
	@Field(name = "dept_name")
	private String deptName;

	public Employee() {
		super();
	}

	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * @return the empId
	 */
	public Long getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the empSal
	 */
	public Double getEmpSal() {
		return empSal;
	}

	/**
	 * @param empSal the empSal to set
	 */
	public void setEmpSal(Double empSal) {
		this.empSal = empSal;
	}

	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
