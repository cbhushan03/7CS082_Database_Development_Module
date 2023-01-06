package com.derby.io.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
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
import com.derby.io.thymeleaf.model.PilotEligibility;
import com.derby.io.thymeleaf.repository.AirlineRepository;
import com.derby.io.thymeleaf.repository.EmployeeRepository;
import com.derby.io.thymeleaf.repository.FlightRepository;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/flights/")
public class FlightController {
	
	@Value("${derby.io.report.path}")
	private String reportPath;

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
		model.addAttribute("flight", new Flight());
		return "add-flight";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("flights", repository.getActiveFlights());
		return "indexFlight";
	}
	
	@PostMapping("search")
	public String showEligibilityData(@ModelAttribute PilotEligibility pilotEligibility,Model model) {
		
		model.addAttribute("listEligible", repository.getPilotEligible(pilotEligibility.getPilot()));
		
		return "indexPilotEligibilty";
	}

	@ModelAttribute("allAirLine")
	List<Airline> getAirLine() {
		return this.airlineRepository.getActiveInvetory();
	}

	@ModelAttribute("allPilots")
	List<Employee> getEmployee() {
		return this.employeeRepository.getActivePilotsEmployee();
	}

	@PostMapping("add")
	public String addDepartment(@Valid @ModelAttribute Flight flight, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-flight";
		}
		repository.saveFlight(flight.getArrivalAirport(), flight.getDepartureAirport(), flight.getArrivalTime(),
				flight.getDepartureTime(), flight.getPilot(), flight.getPlane(), flight.getHours());

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

		repository.updateFlight(flight.getArrivalAirport(), flight.getDepartureAirport(), flight.getArrivalTime(),
				flight.getDepartureTime(), flight.getPilot(), flight.getPlane(), flight.getHours(),
				flight.getFlightNumber());

		model.addAttribute("flights", repository.getActiveFlights());
		return "indexFlight";
	}

	@GetMapping("delete/{flightNumber}")
	public String deleteStudent(@PathVariable("flightNumber") long id, Model model) {
		repository.removeFlight(id);
		model.addAttribute("flights", repository.getActiveFlights());
		return "indexFlight";

	}

	@GetMapping("browsePilotSchedule")
	public String browsePilotSchedule(Model model) {
		model.addAttribute("pilotSchedule", repository.getPilotScheduleList());
		model.addAttribute("order", "DESC");

		return "indexPilotSchedule";

	}

	@GetMapping("browsePilotScheduleDesc")
	public String browsePilotScheduleDesc(Model model) {
		model.addAttribute("pilotSchedule", repository.getPilotScheduleListDesc());
		model.addAttribute("order", "ASC");

		return "indexPilotSchedule";

	}
	
	@GetMapping("viewPilotEligible")
	public String viewPilotEligible(Model model) throws JRException, IOException {
		
		model.addAttribute("pilotEligibility", new PilotEligibility());
		
		return "indexPilotEligibilty";

	}
	
	
	@GetMapping("printPilotScheduleDesc")
	public void printPilotScheduleDesc(Model model,HttpServletResponse response) throws JRException, IOException {
		generateReport("DESC",response);
		model.addAttribute("order", "ASC");

	}
	
	@GetMapping("printPilotSchedule")
	public void printPilotSchedule(Model model,HttpServletResponse response) throws JRException, IOException {
		generateReport("ASC",response);
		model.addAttribute("order", "DESC");

		//return "redirect:/flights/browsePilotSchedule";

	}
	
	public void generateReport(String order,HttpServletResponse response) throws JRException, IOException {
		
		   JasperReport jasperReport;
		    JRDataSource reportSource;
		    Map<String,Object> reportParameters= new HashMap<String, Object>();
		    
		    File filelogo = ResourceUtils.getFile("classpath:bootstrap-logo.svg");
		    
		    File fileJaperReport = ResourceUtils.getFile("classpath:PilotScheduleReport.jrxml");
		    
		    reportParameters.put("paramLogFilePath",filelogo.getAbsolutePath());
		    
		    jasperReport = JasperCompileManager.compileReport(fileJaperReport.getAbsolutePath());
		    
		    if(order.equalsIgnoreCase("ASC")) {
		    	 reportSource = new JRBeanCollectionDataSource(repository.getPilotScheduleList(),false);
		    }else {
		    	 reportSource = new JRBeanCollectionDataSource(repository.getPilotScheduleListDesc(),false);
		    }
		   
		   JasperPrint print = JasperFillManager.fillReport(jasperReport,reportParameters,reportSource);
		    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		    
		    response.setContentType("application/x-download");
		    response.addHeader("Content-disposition", "attachment; filename=PilotScheduleReport_"+timestamp+".pdf");
		    OutputStream out = response.getOutputStream();
		    JasperExportManager.exportReportToPdfStream(print,out);
		    
		    //JasperExportManager.exportReportToPdfFile(print, reportPath+"PilotScheduleReport_"+timestamp+".pdf");
		
	}


}
