package com.derby.io.thymeleaf.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name="Airline")
@Data
public class Airline {
	
	@Id
	private long numsrl;
	
	@NotBlank(message = "Please enter valid Airline Type")
	@Column(name="Airline_Type")
	private String airlineType;
	
	@NotBlank(message = "Please enter valid Airline Name")
	@Column(name="Airline_Name")
	private String airlineName;
	
	@NotBlank(message = "Please enter valid Rating")
	@Column(name="Rating")
	private String rating;
	
	@Column(name="Creation_Date")
	private Timestamp creationDate;
	
	@Column(name="Status")
	private String Status;
}
