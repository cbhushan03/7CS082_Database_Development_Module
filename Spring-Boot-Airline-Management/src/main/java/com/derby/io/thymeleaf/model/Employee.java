package com.derby.io.thymeleaf.model;

import java.math.BigDecimal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Entity
@Table(name="employee")
@Data
public class Employee {
	
	@Id
	@Column(name="Employee_ID")
	private long empid;
	
	@NotBlank(message = "Please enter valid First Name")
	@Column(name="First_Name")
	private String firstname;
	
	@NotBlank(message = "Please enter valid Last Name")
	@Column(name="Last_Name")
	private String lastname;
	
	@NotBlank(message = "Please enter vaild date in yyyy-mm-dd format")
	@Column(name="Date_of_joining")
	private String dateofjoin;
	
	@NotBlank(message = "Please enter valid Phone number")
	@Column(name="Phone_number")
	private String phone;
	
	@NotBlank(message = "Please enter valid address")
	@Column(name="Address")
	private String address;
	
	@NotNull(message = "Please enter valid salary")
	@Column(name="salary")
	private BigDecimal salary;
	
	
	@Column(name="rating")
	private String rating;

	@Column(name="status")
	private String status;
	
	@NotNull(message = "Please enter valid Department Id")
	@Column(name="Department_ID")
	private long departmentid; 
	
	@Column(name="Department_name")
	private String departmentname;
}
