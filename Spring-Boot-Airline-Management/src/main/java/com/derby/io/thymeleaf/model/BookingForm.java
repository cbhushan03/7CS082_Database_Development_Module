package com.derby.io.thymeleaf.model;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class BookingForm {
	private long bookingId;
	private long flightId;
	
	private List<Passenger> passengers = new ArrayList<Passenger>();
	
	private long passengerId;
	
	private String flightDate;
}
