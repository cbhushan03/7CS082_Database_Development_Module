package com.derby.io.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.derby.io.thymeleaf.model.Employee;
import com.derby.io.thymeleaf.repository.EmployeeRepository;





@Controller
@RequestMapping("/employees/")
public class EmployeeController {
	
	private final EmployeeRepository repository;
	
	public EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
		
	}
	
	 @GetMapping("signup")
	    public String showSignUpForm(Employee employee) {
	        return "add-employee";
	    }

	    @GetMapping("list")
	    public String showUpdateForm(Model model) {
	        model.addAttribute("employees", repository.getActiveEmployee());
	        return "indexEmployee";
	    }

	    @PostMapping("add")
	    public String addEmployee(@Valid Employee employee, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "add-employee";
	        }

	        repository.saveEmployee(employee.getFirstname(), employee.getLastname(), employee.getDateofjoin(), 
	        		employee.getPhone(), employee.getAddress(), employee.getSalary(), employee.getRating(), employee.getDepartmentid());
	        return "redirect:list";
	    }

	    @GetMapping("edit/{empid}")
	    public String showUpdateForm(@PathVariable("empid") long id, Model model) {
	        Employee airline= repository.getSingleActiveEmployee(id) ;
	        
	        model.addAttribute("employee", airline);
	        return "update-employee";
	    }

	    @PostMapping("update/{empid}")
	    public String updateStudent(@PathVariable("empid") long id, @Valid Employee employee, BindingResult result,
	        Model model) {
	        if (result.hasErrors()) {
	            employee.setEmpid(id);
	            return "update-employee";
	        }
	        
	        employee.setStatus("A");
	        repository.updateEmployee(employee.getFirstname(), employee.getLastname(), employee.getDateofjoin(), 
	        		employee.getPhone(), employee.getAddress(), employee.getSalary(), employee.getRating(), employee.getDepartmentid(),employee.getEmpid());
	        
	        model.addAttribute("employees", repository.getActiveEmployee());
	        return "indexEmployee";
	    }

	    @GetMapping("delete/{empid}")
	    public String deleteStudent(@PathVariable("empid") long id, Model model) {
	        Employee employee = repository.getSingleActiveEmployee(id);
	        repository.removeEmployee(employee.getEmpid());
	        model.addAttribute("employee", repository.getActiveEmployee());
	        return "indexEmployee";
	    }
	    
	    
}
