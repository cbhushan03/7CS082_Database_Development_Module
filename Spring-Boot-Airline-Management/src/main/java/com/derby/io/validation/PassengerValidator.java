package com.derby.io.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.derby.io.thymeleaf.model.Passenger;

public class PassengerValidator implements ConstraintValidator<PassengerConstraint,List<Passenger>> {

	@Override
    public void initialize(PassengerConstraint passengerConstraint) {
		
	}
	
	@Override
	public boolean isValid(List<Passenger> listPassenger, ConstraintValidatorContext context) {
		
		return (listPassenger == null || listPassenger.size() == 0) ? false : true;
	}
	
	

}
