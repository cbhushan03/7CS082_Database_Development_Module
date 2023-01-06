package com.derby.io.thymeleaf.model;

import javax.persistence.Embeddable;
import javax.persistence.Table;

import lombok.Data;

@Embeddable
@Table(name="flightEligible")
@Data
public class PilotEligibility {
	
	private int pilot;
	
	private String airlinename;
	private String status;
}
