package com.derby.io.thymeleaf.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "Department")
public class Department {
	
	@Id
	@Column(name = "Department_ID")
	private long departmentId;
	
	@NotBlank(message = "This field is mandatory")
	@Column(name = "Department_Name")
	private String detaprtmentName;
	
	@Column(name = "Creation_date")
	private Timestamp creationDate;
}
