package com.derby.io.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.derby.io.thymeleaf.model.Passenger;
import com.derby.io.thymeleaf.repository.PassangerRepository;





@Controller
@RequestMapping("/passengers/")
public class PassengerController {
	
	private final PassangerRepository repository;
	
	public PassengerController(PassangerRepository repository) {
		this.repository = repository;
		
	}
	
	 @GetMapping("signup")
	    public String showSignUpForm(Passenger passanger) {
	        return "add-passenger";
	    }

	    @GetMapping("list")
	    public String showUpdateForm(Model model) {
	        return showPageSearch(model,1);
	    }
	    
	    @GetMapping("pageSearch/{pageNumber}")
	    public String showPageSearch(Model model,@PathVariable("pageNumber") int pageNumber) {
	    	
	    	Page<Passenger> page = repository.getActivePassenger(PageRequest.of(pageNumber-1,8));
	    	int totalPage = page.getTotalPages();
	    	long totalItem = page.getTotalElements();
	    	List<Passenger> listPassenger= page.getContent();
	    	
	    	
	    	model.addAttribute("currentPage", pageNumber);
	    	model.addAttribute("totalPage", totalPage);
	    	model.addAttribute("totalItem", totalItem);
	    	
	    	model.addAttribute("passengers", listPassenger);
		    return "indexPassenger";
	    }

	    @PostMapping("add")
	    public String addPassenger(@Valid Passenger passenger, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "add-passenger";
	        }

	        repository.savePassenger(passenger.getFirstname(), passenger.getLastname(), passenger.getEmail(), passenger.getPhone(), passenger.getAddress());
	        return "redirect:list";
	    }

	    @GetMapping("edit/{passengerId}")
	    public String showUpdateForm(@PathVariable("passengerId") long id, Model model) {
	        Passenger passenger= repository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid Airline Id:" + id));
	        model.addAttribute("passenger", passenger);
	        return "update-passenger";
	    }

	    @PostMapping("update/{passengerId}")
	    public String updateStudent(@PathVariable("passengerId") long id, @Valid Passenger passenger, BindingResult result,
	        Model model) {
	        if (result.hasErrors()) {
	            passenger.setPassengerId(id);
	            return "update-airline";
	        }
	        
	        passenger.setStatus("A");
	        passenger.setCreationDate(new Timestamp(System.currentTimeMillis()));
	        repository.save(passenger);
	        model.addAttribute("passengers", repository.getActivePassenger());
	        return "indexPassenger";
	    }

	    @GetMapping("delete/{passengerId}")
	    public String deleteStudent(@PathVariable("passengerId") long id, Model model) {
	        Passenger passenger = repository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid Airline Id:" + id));
	        repository.removePassenger(passenger.getPassengerId());
	        model.addAttribute("passengers", repository.getActivePassenger());
	        return "indexPassenger";
	    }
}
