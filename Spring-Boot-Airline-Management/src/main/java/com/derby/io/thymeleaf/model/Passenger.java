package com.derby.io.thymeleaf.model;



import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


import lombok.Data;

@Entity
@Table(name="passenger")
@Data
public class Passenger {
	
	@Id
	private long passengerId;
	
	@NotBlank(message = "Please enter valid First Name")
	@Column(name="First_Name")
	private String firstname;
	
	@NotBlank(message = "Please enter valid Last Name")
	@Column(name="Last_Name")
	private String lastname;
	
	@NotBlank(message = "Please enter valid email")
	@Email(message = "Please enter valid email")
	@Column(name="email")
	private String email;
	
	@NotBlank(message = "Please enter valid Phone number")
	@Column(name="Phone_number")
	private String phone;
	
	@NotBlank(message = "Please enter valid address")
	@Column(name="Address")
	private String address;
	
	@Column(name="status")
	private String status;
	
	@Column(name = "Creation_date")
	private Timestamp creationDate;
	
}
