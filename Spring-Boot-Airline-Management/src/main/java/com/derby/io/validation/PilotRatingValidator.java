package com.derby.io.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.derby.io.thymeleaf.model.Flight;
import com.derby.io.thymeleaf.repository.AirlineRepository;
import com.derby.io.thymeleaf.repository.EmployeeRepository;
import com.derby.io.thymeleaf.repository.FlightRepository;

public class PilotRatingValidator implements ConstraintValidator<RatingConstraint, Flight> {
	
	@Autowired
	private FlightRepository repository;
	
	@Autowired
	private AirlineRepository airRepository;
	
	@Autowired
	private EmployeeRepository empRepository;
	
	
	
	@Override
    public void initialize(RatingConstraint contactNumber) {
    }

	@Override
	public boolean isValid(Flight flight, ConstraintValidatorContext context) {
		
		return checkRating(flight);
	}
	
	
	public boolean checkRating(Flight flight) {
		
		String empRating = empRepository.getEmployeeRating(flight.getPilot());
		
		String airlineRating = airRepository.getAirlineRating(flight.getPlane());
		
		return empRating.equals(airlineRating);
	
	}

}
