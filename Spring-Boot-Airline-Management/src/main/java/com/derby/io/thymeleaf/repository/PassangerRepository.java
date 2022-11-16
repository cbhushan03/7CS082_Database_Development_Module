package com.derby.io.thymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.derby.io.thymeleaf.model.Passenger;

public interface PassangerRepository extends JpaRepository<Passenger, Long> {
	
	@Query(value = "select a.* from passenger a where status='A' ",nativeQuery = true)
	List<Passenger> getActivePassenger();
	
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO passenger (First_Name, Last_Name, email, Phone_number, Address) "
			+ "	VALUES(:fname, :lname, :email, :phone, :address) ",nativeQuery =true)
	void savePassenger(@Param(value = "fname") String fname,
			@Param(value = "lname") String lname,
			@Param(value = "email") String email,
			@Param(value = "phone") String phone,
			@Param(value = "address") String address
			);

	
	
	@Transactional
	@Modifying
	@Query(value = "update passenger set status='D' where Passenger_ID =:id  ",nativeQuery =true)
	void removePassenger(@Param(value = "id") long id);
}
