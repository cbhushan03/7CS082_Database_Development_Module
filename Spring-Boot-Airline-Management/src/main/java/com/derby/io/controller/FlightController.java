package com.derby.io.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.derby.io.thymeleaf.model.AddFlight;
import com.derby.io.thymeleaf.model.Airline;
import com.derby.io.thymeleaf.model.Employee;
import com.derby.io.thymeleaf.model.Flight;
import com.derby.io.thymeleaf.repository.AirlineRepository;
import com.derby.io.thymeleaf.repository.EmployeeRepository;
import com.derby.io.thymeleaf.repository.FlightRepository;


@Controller
@RequestMapping("/flights/")
public class FlightController {
	
	private final FlightRepository repository;
	
	@Autowired
	private AirlineRepository airlineRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public FlightController(FlightRepository repository) {
		this.repository = repository;
		
	}
	
	   @GetMapping("signup")
	    public String showSignUpForm(Model model) {
		   	model.addAttribute("flight",new Flight());
	        return "add-flight";
	    }

	    @GetMapping("list")
	    public String showUpdateForm(Model model) {
	        model.addAttribute("flights", repository.getActiveFlights());
	        return "indexFlight";
	    }
	    
	    @ModelAttribute("allAirLine")
	    List<Airline> getAirLine(){
	    	return this.airlineRepository.getActiveInvetory();
	    }
	    
	    @ModelAttribute("allPilots")
	    List<Employee> getEmployee(){
	    	return this.employeeRepository.getActivePilotsEmployee();
	    }
	    
	    
	
	@PostMapping("add")
	public String addDepartment(@Valid @ModelAttribute Flight flight, BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "add-flight";
		}
		repository.saveFlight(flight.getArrivalAirport(),flight.getDepartureAirport(),flight.getArrivalTime(),flight.getDepartureTime(),flight.getPilot(),flight.getPlane());
		
		return "redirect:list";
	}
	 

	
	@GetMapping("edit/{flightNumber}")
	public String showUpdateForm(@PathVariable("flightNumber") long id, Model model) {
		Flight flight = repository.getSingleActiveFlight(id);
		
		model.addAttribute("flight", flight);
		return "update-flight";
	}

	@PostMapping("update/{flightNumber}")
	public String updateStudent(@PathVariable("flightNumber") long id, @Valid @ModelAttribute Flight flight,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			flight.setFlightNumber(id);
			return "update-flight";
		}

		repository.updateFlight(flight.getArrivalAirport(),flight.getDepartureAirport(),flight.getArrivalTime(),
				flight.getDepartureTime(),flight.getPilot(),flight.getPlane(),flight.getFlightNumber());
		
		model.addAttribute("flights", repository.getActiveFlights());
		return "index";
	}
	 

	/*
	 * @GetMapping("delete/{departmentId}") public String
	 * deleteStudent(@PathVariable("departmentId") long id, Model model) {
	 * Department department = repository.findById(id) .orElseThrow(() -> new
	 * IllegalArgumentException("Invalid department Id:" + id));
	 * repository.delete(department); model.addAttribute("departments",
	 * repository.findAll()); return "index"; }
	 */
}
