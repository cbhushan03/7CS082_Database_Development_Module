package com.derby.io.thymeleaf.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import com.derby.io.thymeleaf.model.Flight;
import com.derby.io.thymeleaf.model.PilotEligibility;
import com.derby.io.thymeleaf.model.PilotEligibilityImpl;
import com.derby.io.thymeleaf.model.PilotSchedule;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	
	@Query(value = "select f.*,concat(e.First_Name,', ',e.Last_Name) as pilot_Name , a.Airline_Name as plane_Name from flight f inner join employee e on f.Pilot_ID = e.Employee_ID inner join airline a "
			+ "on f.Plane_ID = a.NUMSRL where f.status='A' ",nativeQuery = true)
	List<Flight> getActiveFlights();
	
		
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO flight (Arrival_Airport, Departure_Airport, Arrival_Time, Departure_Time, Pilot_ID, Plane_ID,hours) VALUES\r\n" + 
			"(:arrivalAir, :departAir, :aTime, :dTime, :pilot, :plane, :hours) ",nativeQuery =true)
	void saveFlight(@Param(value = "arrivalAir") String arrivalAir,
			@Param(value = "departAir") String departAir,
			@Param(value = "aTime") String aTime,
			@Param(value = "dTime") String dTime,
			@Param(value = "pilot") long pilot,
			@Param(value = "plane") long plane,
			@Param(value = "hours") int hours
			);
	
	@Query(value = "select f.*,concat(e.First_Name,', ',e.Last_Name) as pilot_Name , a.Airline_Name as plane_Name from flight f inner join employee e on f.Pilot_ID = e.Employee_ID inner join airline a "
			+ "on f.Plane_ID = a.NUMSRL where f.status='A' and f.Flight_number=:id ",nativeQuery = true)
	Flight getSingleActiveFlight(@Param(value = "id") long id);
	
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE flight SET Arrival_Airport=:arrivalAir, Departure_Airport=:departAir, Arrival_Time=:aTime, Departure_Time=:dTime, Pilot_ID=:pilot, Plane_ID=:plane, hours=:hours "
			+ " WHERE Flight_number=:fNum" ,nativeQuery =true)
	void updateFlight(@Param(value = "arrivalAir") String arrivalAir,
			@Param(value = "departAir") String departAir,
			@Param(value = "aTime") String aTime,
			@Param(value = "dTime") String dTime,
			@Param(value = "pilot") long pilot,
			@Param(value = "plane") long plane,
			@Param(value = "hours") int hours,
			@Param(value = "fNum") long fNum
			);
	
	
	
	
	@Transactional
	@Modifying
	@Query(value = "update flight set status='D' where Flight_number=:id  ",nativeQuery =true)
	void removeFlight(@Param(value = "id") long id);
	
	@Query(value="select p.* from pilotschedule p  order by p.totalhours asc",nativeQuery=true)
	List<PilotSchedule> getPilotScheduleList();
	
	@Query(value="select p.* from pilotschedule p order by p.totalhours desc",nativeQuery=true)
	List<PilotSchedule> getPilotScheduleListDesc();
	
	@Query(value="select count(f.Flight_number) as CNT from 	flight f inner join employee e on 	f.Pilot_ID = e.Employee_ID inner join airline a on 	f.Plane_ID = a.NUMSRL where 	f.Pilot_ID = :pilot 	and f.Plane_ID = :plane 	and e.Rating  = a.Rating ",nativeQuery=true)
	int ratingEligibilityCheck(@Param(value = "pilot") long pilot,
			@Param(value = "plane") long plane);
	
	@Query(value="select * from (\r\n"
			+ "select a.Airline_Name as airlinename , 'Eligible' as Status  from airline a where a.Rating  in (select Rating  from employee e where e.Employee_ID=:pilot)\r\n"
			+ "union all\r\n"
			+ "select a.Airline_Name as airlinename , 'Not Eligible' as Status  from airline a where a.Rating  not in (select Rating  from employee e where e.Employee_ID=:pilot)  ) as flightEligible\r\n"
			+ "order by airlinename",nativeQuery=true)
	List<PilotEligibilityImpl> getPilotEligible(@Param(value="pilot") int pilot);


}
