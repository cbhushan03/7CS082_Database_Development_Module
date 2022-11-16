package com.derby.io.controller;

import java.sql.Timestamp;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.derby.io.thymeleaf.model.Department;
import com.derby.io.thymeleaf.repository.DepartmentRepository;


@Controller
@RequestMapping("/departments/")
public class DepartmentController {
	
	private final DepartmentRepository repository;
	
	public DepartmentController(DepartmentRepository repository) {
		this.repository = repository;
		
	}
	
	 @GetMapping("signup")
	    public String showSignUpForm(Department department) {
	        return "add-department";
	    }

	    @GetMapping("list")
	    public String showUpdateForm(Model model) {
	        model.addAttribute("departments", repository.findAll());
	        return "index";
	    }

	    @PostMapping("add")
	    public String addDepartment(@Valid Department department, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "add-department";
	        }

	        repository.saveDepartment(department.getDetaprtmentName());
	        return "redirect:list";
	    }

	    @GetMapping("edit/{departmentId}")
	    public String showUpdateForm(@PathVariable("departmentId") long id, Model model) {
	        Department department= repository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + id));
	        model.addAttribute("department", department);
	        return "update-department";
	    }

	    @PostMapping("update/{departmentId}")
	    public String updateStudent(@PathVariable("departmentId") long id, @Valid Department department, BindingResult result,
	        Model model) {
	        if (result.hasErrors()) {
	            department.setDepartmentId(id);
	            return "update-department";
	        }
	        
	        department.setCreationDate(new Timestamp(System.currentTimeMillis()));
	        repository.save(department);
	        model.addAttribute("departments", repository.findAll());
	        return "index";
	    }

	    @GetMapping("delete/{departmentId}")
	    public String deleteStudent(@PathVariable("departmentId") long id, Model model) {
	        Department department = repository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + id));
	        repository.delete(department);
	        model.addAttribute("departments", repository.findAll());
	        return "index";
	    }
}
