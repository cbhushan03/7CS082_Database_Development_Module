package com.derby.io.thymeleaf.model;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.derby.io.validation.PassengerConstraint;

import lombok.Data;


@Data
public class BookingForm {
	private long bookingId;
	private long flightId;
	
	@PassengerConstraint
	private List<Passenger> passengers = new ArrayList<Passenger>();
	
	private long passengerId;
	
	private String flightDate;
}
