package com.derby.io.thymeleaf.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.derby.io.thymeleaf.model.Booking;
import com.derby.io.thymeleaf.model.BookingForm;
import com.derby.io.thymeleaf.model.Employee;
import com.derby.io.thymeleaf.model.Flight;
import com.derby.io.thymeleaf.model.PassengerBookingReport;


public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	@Query(value = "select b.Booking_ID ,b.Flight_number,b.Passenger_ID, f.Departure_Airport as source, f.Arrival_Airport as destination , f.Arrival_Time  , f.Departure_Time  ,  concat(p.First_Name,', ',p.Last_Name) as passanger_Name , b.Booking_Date , b.flight_Date, b.status   from booking b inner join flight f on b.Flight_number = f.Flight_number inner join passenger p on b.Passenger_ID = p.Passenger_ID where b.status ='A'",nativeQuery = true)
	List<Booking> getActiveBooking();
	
	
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO booking (Booking_Date,Flight_number, Passenger_ID, Flight_Date) VALUES(CURRENT_DATE,:flightNumber, :passengerID, :flightDate) ",nativeQuery =true)
	void saveBooking(@Param(value = "flightNumber") long flightNumber,
		@Param(value = "passengerID") long passengerId,
		@Param(value = "flightDate") String flightDate
		
	);
	
	 
	  @Query(value = "select b.Booking_ID, b.Flight_number,b.Passenger_ID, f.Departure_Airport as source, f.Arrival_Airport as destination , f.Arrival_Time  , f.Departure_Time  ,  concat(p.First_Name,', ',p.Last_Name) as passanger_Name , b.Booking_Date , b.flight_Date, b.status   from booking b inner join flight f on b.Flight_number = f.Flight_number inner join passenger p on b.Passenger_ID = p.Passenger_ID where b.status ='A' and b.Booking_ID=:id",nativeQuery = true) 
	//@Query(value = "select Booking_ID,Flight_number,Passenger_ID,Flight_Date from booking where Booking_ID=:id ",nativeQuery = true)  
	Booking getSingleActiveBooking(@Param(value = "id") long id);
	  
	  
	  @Transactional
	  @Modifying
	  @Query(value ="update booking set Flight_number=:flightNumber, Passenger_ID=:passengerID, Flight_Date=:flightDate where Booking_ID=:id  " ,nativeQuery =true) void
	  updateBooking(@Param(value = "flightNumber") long flightNumber,
				@Param(value = "passengerID") long passengerId,
				@Param(value = "flightDate") String flightDate,
				@Param(value = "id") long id
			);
	 
	
	
	
	@Transactional
	@Modifying
	@Query(value = "update booking set status='D' where Booking_ID=:id  ",nativeQuery =true)
	void removeBooking(@Param(value = "id") long id);
	
	@Query(value = "select * from PASSENGERBOOKINGREPORT p order by p.JOURNEYDATE  ",nativeQuery = true)
	List<PassengerBookingReport> getBookedPassengerData();


}
