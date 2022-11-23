package com.derby.io.thymeleaf.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Data
@Entity
@Table(name="booking")
public class Booking {
	
	@Id
	@Column(name="Booking_ID")
	private long bookingId;
	
	@Column(name="source")
	private String source;
	
	@Column(name="destination")
	private String destination;
	
	
	@Column(name="arrival_Time")
	private Time arrivalTime;
	
	
	@Column(name="departure_Time")
	private Time departureTime;
	
	@Column(name="passangerName")
	private String passengerName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="booking_Date")
	private Date bookingDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="flight_Date")
	private Date flightDate;
	
	@Column(name="status")
	private String status;
	
	@Column(name="Flight_number")
	private long flightId;
	
	@Column(name="Passenger_ID")
	private long passengerId; 
	

}
