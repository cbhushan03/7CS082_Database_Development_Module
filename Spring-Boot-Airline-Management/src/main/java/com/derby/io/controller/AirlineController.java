package com.derby.io.controller;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.derby.io.thymeleaf.model.Airline;
import com.derby.io.thymeleaf.model.Rating;
import com.derby.io.thymeleaf.repository.AirlineRepository;



@Controller
@RequestMapping("/airlines/")
public class AirlineController {
	
	private final AirlineRepository repository;
	
	public AirlineController(AirlineRepository repository) {
		this.repository = repository;
		
	}
	
	 @GetMapping("signup")
	    public String showSignUpForm(Airline airline) {
	        return "add-airline";
	    }

	    @GetMapping("list")
	    public String showUpdateForm(Model model) {
	        model.addAttribute("airlines", repository.getActiveInvetory());
	        return "indexAirline";
	    }

	    @PostMapping("add")
	    public String addAirline(@Valid Airline airline, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "add-airline";
	        }

	        repository.saveAirline(airline.getAirlineType(),airline.getAirlineName(),airline.getRating());
	        return "redirect:list";
	    }

	    @GetMapping("edit/{numsrl}")
	    public String showUpdateForm(@PathVariable("numsrl") long id, Model model) {
	        Airline airline= repository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid Airline Id:" + id));
	        model.addAttribute("airline", airline);
	        return "update-airline";
	    }

	    @PostMapping("update/{numsrl}")
	    public String updateStudent(@PathVariable("numsrl") long id, @Valid Airline airline, BindingResult result,
	        Model model) {
	        if (result.hasErrors()) {
	            airline.setNumsrl(id);
	            return "update-airline";
	        }
	        
	        airline.setStatus("A");
	        airline.setCreationDate(new Timestamp(System.currentTimeMillis()));
	        repository.save(airline);
	        model.addAttribute("airlines", repository.getActiveInvetory());
	        return "indexAirline";
	    }

	    @GetMapping("delete/{numsrl}")
	    public String deleteStudent(@PathVariable("numsrl") long id, Model model) {
	        Airline department = repository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid Airline Id:" + id));
	        repository.removeAirline(department.getNumsrl());
	        model.addAttribute("airlines", repository.getActiveInvetory());
	        return "indexAirline";
	    }
	    
	    @ModelAttribute("allRating")
	    List<Rating> getRating(){
	    	return Arrays.asList(Rating.ALL);
	    }
}
