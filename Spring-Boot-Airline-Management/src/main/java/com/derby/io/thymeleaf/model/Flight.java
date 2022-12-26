package com.derby.io.thymeleaf.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="Flight")
@Data
public class Flight {
	
	@Id
	@Column(name="Flight_number")
	private long flightNumber;
	
	@NotBlank(message = "Please enter valid Arrival Airport Name")
	@Column(name="Arrival_Airport")
	private String arrivalAirport;
	
	@NotBlank(message = "Please enter valid Departure Airport Name")
	@Column(name="Departure_Airport")
	private String departureAirport;
	
	@NotBlank(message = "Please enter valid Arrival Airport Time")
	@Column(name="Arrival_Time")
	private String arrivalTime;
	
	@NotBlank(message = "Please enter valid Departure Airport Time")
	@Column(name="Departure_Time")
	private String departureTime;
	
	
	private String planeName;
	
	@Column(name="Plane_ID")
	private long plane;
	
	@Column(name="Pilot_ID")
	private long pilot;
	
	
	private String pilotName;
	
	@NotNull(message = "Please enter valid Journey hour time")
	@Column(name="hours")
	private int hours;
	
}
