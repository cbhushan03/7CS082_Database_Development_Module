package com.derby.io.thymeleaf.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AddFlight {
		@NotBlank(message = "Please enter valid Arrival Airport Name")
		private String arrivalAirport;
		
		@NotBlank(message = "Please enter valid Departure Airport Name")
		private String departureAirport;
		
		@NotBlank(message = "Please enter valid Arrival Airport Time")
		private String arrivalTime;
		
		@NotBlank(message = "Please enter valid Departure Airport Time")
		private String departureTime;
		
		
		private Airline airline;
		private long pilot;
		private long plane;
		private Employee employee;
}
