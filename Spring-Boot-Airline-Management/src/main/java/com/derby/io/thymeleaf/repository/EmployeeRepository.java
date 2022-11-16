package com.derby.io.thymeleaf.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.derby.io.thymeleaf.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query(value = "select a.*,d.Department_Name  from employee a inner join department d on a.department_id= d.department_id where a.status='A' ",nativeQuery = true)
	List<Employee> getActiveEmployee();
	
	@Query(value = "select a.*,d.Department_Name  from employee a inner join department d on a.department_id= d.department_id where a.status='A' and a.Department_ID ='3' and a.Rating <> '' ",nativeQuery = true)
	List<Employee> getActivePilotsEmployee();
	
	
	@Query(value = "select a.*,d.Department_Name  from employee a inner join department d on a.department_id= d.department_id where a.status='A' and Employee_ID=:id ",nativeQuery = true)
	Employee getSingleActiveEmployee(@Param(value = "id") long id);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO employee (First_Name, Last_Name, Date_of_joining, Phone_number, Address, Salary, Rating, Department_ID) "
			+ "	VALUES(:fname, :lname, :doj, :phone, :address, :salary, :rating, :deptID) ",nativeQuery =true)
	void saveEmployee(@Param(value = "fname") String fname,
			@Param(value = "lname") String lname,
			@Param(value = "doj") String doj,
			@Param(value = "phone") String phone,
			@Param(value = "address") String address,
			@Param(value = "salary") BigDecimal salary,
			@Param(value = "rating") String rating,
			@Param(value = "deptID") long deptid
			);
	
	
	@Transactional
	@Modifying
	@Query(value = "update employee set First_Name=:fname, Last_Name=:lname, Date_of_joining=:doj, "
			+ "Phone_number=:phone, Address=:address, Salary=:salary, Rating=:rating, Department_ID=:deptID  "
			+ "	where Employee_ID=:id ",nativeQuery =true)
	void updateEmployee(@Param(value = "fname") String fname,
			@Param(value = "lname") String lname,
			@Param(value = "doj") String doj,
			@Param(value = "phone") String phone,
			@Param(value = "address") String address,
			@Param(value = "salary") BigDecimal salary,
			@Param(value = "rating") String rating,
			@Param(value = "deptID") long deptid,
			@Param(value = "id") long id
			);
	
	
	@Transactional
	@Modifying
	@Query(value = "update employee set status='D' where Employee_ID=:id  ",nativeQuery =true)
	void removeEmployee(@Param(value = "id") long id);


}
