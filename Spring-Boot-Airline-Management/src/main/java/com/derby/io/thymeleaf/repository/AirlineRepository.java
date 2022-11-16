package com.derby.io.thymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.derby.io.thymeleaf.model.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
	
	
	@Query(value = "select a.* from Airline a where status='A' ",nativeQuery = true)
	List<Airline> getActiveInvetory();
	
	@Transactional
	@Modifying
	@Query(value = "insert into airline(Airline_Type,Airline_Name,Rating) values(:airline_type,:airline_name,:rating) ",nativeQuery =true)
	void saveAirline(@Param(value = "airline_type") String airline_type,
			@Param(value = "airline_name") String airline_name,
			@Param(value = "rating") String rating);


	@Transactional
	@Modifying
	@Query(value = "update airline set status='D' where numsrl=:id  ",nativeQuery =true)
	void removeAirline(@Param(value = "id") long id);

}
