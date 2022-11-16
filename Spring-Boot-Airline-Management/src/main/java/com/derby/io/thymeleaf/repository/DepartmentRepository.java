package com.derby.io.thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.derby.io.thymeleaf.model.Department;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	@Transactional
	@Modifying
	@Query(value = "insert into department(department_name) values(:department_name) ",nativeQuery =true)
	void saveDepartment(@Param(value = "department_name") String department_name);
	
}
