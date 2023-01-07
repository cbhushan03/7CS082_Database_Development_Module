package com.derby.io.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.derby.io.thymeleaf.model.Booking;
import com.derby.io.thymeleaf.model.BookingForm;
import com.derby.io.thymeleaf.model.Flight;
import com.derby.io.thymeleaf.model.Passenger;
import com.derby.io.thymeleaf.model.PassengerBookingReport;
import com.derby.io.thymeleaf.model.PilotEligibility;
import com.derby.io.thymeleaf.model.SearchBookingForm;
import com.derby.io.thymeleaf.repository.BookingRepository;
import com.derby.io.thymeleaf.repository.FlightRepository;
import com.derby.io.thymeleaf.repository.PassangerRepository;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;



@Controller
@RequestMapping("/booking/")
public class BookingController {
	
	Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	@Value("${derby.io.report.path}")
	private String reportPath;
	
	private final BookingRepository repository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private PassangerRepository passangerRepository;

	
	public BookingController(BookingRepository repository) {
		this.repository = repository;
		
	}
	
	
	  @GetMapping("signup") 
	  public String showSignUpForm(Model model) {
		  BookingForm form = new BookingForm();
		  //form.setPassengers(this.passangerRepository.getActivePassenger());
		  model.addAttribute("booking",form); 
		  
		 
		  return "add-booking"; 
	  }
	 
	    @GetMapping("list")
	    public String showUpdatedForm(Model model) {
	    	
	    	/*model.addAttribute("booking", repository.getActiveBooking());
	        model.addAttribute("bookingSearchForm",new SearchBookingForm()); */
	        
	    	return showPageSearch(model,1);
	    }
	    
	    
	    @GetMapping("pageSearch/{pageNumber}")
	    public String showPageSearch(Model model,@PathVariable("pageNumber") int pageNumber) {
	    	
	    	Page<Booking> page = findBookingPage(pageNumber);
	    	
	    	int totalPage = page.getTotalPages();
	    	long totalItem = page.getTotalElements();
	    	
	    	List<Booking> bookings = page.getContent();
	    	
	    	model.addAttribute("currentPage", pageNumber);
	    	model.addAttribute("totalPage", totalPage);
	    	model.addAttribute("totalItem", totalItem);
	    	
	        model.addAttribute("booking", bookings);
	        model.addAttribute("bookingSearchForm",new SearchBookingForm()); 
	        return "indexBooking";
	        
	    }
	    
	    @ModelAttribute("allFlight")
	    List<Flight> getFlight(){
	    	return this.flightRepository.getActiveFlights();
	    }
	    
	    @ModelAttribute("allPassenger")
	    List<Passenger> getPassenger(){
	    	return this.passangerRepository.getActivePassenger();
	    }
	    
	    
	
	
	  @PostMapping("add") 
	  public String addDepartment(@Valid @ModelAttribute("booking") BookingForm
	  booking, BindingResult result, Model model) 
	  { 
		  if (result.hasErrors()) {
			     model.addAttribute("errorPassenger", "Please add atleast one passenger to proceed for booking.");
				return "add-booking";
			}
		  
		  for(Passenger passenger : booking.getPassengers()) {
			  repository.saveBooking(booking.getFlightId(),passenger.getPassengerId(),booking.getFlightDate());  
		  }
		  
	  
	  return "redirect:list"; 
	  
	  }
	 
	  @PostMapping(value = "add", params = {"addRow"})
	    public String addPassengerArgument(BookingForm form, BindingResult bindingResult,Model model){
	        if(null!=form){
	            
	                form.getPassengers().add(new Passenger());
	                model.addAttribute("booking", form);
	        }
	        return "add-booking"; 
	    }
	 

	  @PostMapping(value = "add", params = {"removeRow"})
	    public String removePassengerArgument(BookingForm form, BindingResult bindingResult,HttpServletRequest req,Model model){
	        if(null!=form){
	            	
	        	 	final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	                form.getPassengers().remove(rowId.intValue());
	                model.addAttribute("booking", form);
	        }
	        return "add-booking"; 
	    }
	 

	
	
	  @GetMapping("edit/{bookingId}") 
	  public String showUpdateForm(@PathVariable("bookingId") long id, Model model) 
	  {
		  Booking booking = repository.getSingleActiveBooking(id);
	  
		  model.addAttribute("booking", booking); 
		  return "update-booking"; 
	  }
	 

	
	  @PostMapping("update/{bookingId}") 
	  public String updateBooking(@PathVariable("bookingId") long id, 
			  BookingForm booking, Model model) { 
		 
		 repository.updateBooking(booking.getFlightId(),booking.getPassengerId(),booking.getFlightDate(),booking.getBookingId());
		  model.addAttribute("booking", repository.getActiveBooking());
		  return "indexBooking";
	  
	  }
	 
	 

	
	  @GetMapping("delete/{bookingId}") 
	  public String
	  deleteStudent(@PathVariable("bookingId") long id, Model model) {
		  
		  repository.removeBooking(id);
		  model.addAttribute("booking", repository.getActiveBooking());
		  return "indexBooking"; 
	  }
	  
	  @GetMapping("browsePassengerReport") 
	  public String getPassengerReport(Model model) {
		  
		  return getPassengerReport(model,1); 
	  }
	  
	  @GetMapping("browsePassengerReportPage/{pageNumber}") 
	  public String getPassengerReport(Model model,@PathVariable("pageNumber") int pageNumber) {
		  
		   Page<PassengerBookingReport> passengerBookingReport  = repository.getBookedPassengerDataPagable(PageRequest.of(pageNumber-1,8));
		  
		    int totalPage = passengerBookingReport.getTotalPages();
	    	long totalItem = passengerBookingReport.getTotalElements();
	    	
	    	List<PassengerBookingReport> PassengerBookingReportList = passengerBookingReport.getContent();
	    	
	    	model.addAttribute("currentPage", pageNumber);
	    	model.addAttribute("totalPage", totalPage);
	    	model.addAttribute("totalItem", totalItem);
		  
		  
		  model.addAttribute("passengerReport", PassengerBookingReportList);
		  return "indexPassengerReport"; 
	  }
	  
	  
	  
	  @GetMapping("printPassengerReport") 
	  public void
	  printPassengerReport(HttpServletResponse response) throws JRException, IOException, URISyntaxException {
		  	
		    
		    JasperReport jasperReport;
		    JRDataSource reportSource;
		    Map<String,Object> reportParameters= new HashMap<String, Object>();
		   
		    InputStream fileJaperReport = getClass().getClassLoader().getResourceAsStream("PassengerReportCount.jrxml");
		    
		
		    
		    reportParameters.put("paramLogFilePath",null);
		    
		    jasperReport = JasperCompileManager.compileReport(fileJaperReport);
		   
		    reportSource = new JRBeanCollectionDataSource(repository.getBookedPassengerData(),false);
		    
		    JasperPrint print = JasperFillManager.fillReport(jasperReport,reportParameters,reportSource);
		    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		    //JasperExportManager.exportReportToPdfFile(print, reportPath+"PassengerReport_"+timestamp+".pdf");
		    
		    response.setContentType("application/x-download");
		    response.addHeader("Content-disposition", "attachment; filename=PassengerReport_"+timestamp+".pdf");
		    OutputStream out = response.getOutputStream();
		    JasperExportManager.exportReportToPdfStream(print,out);
		    
	
	  }
	  
	  @PostMapping("search")
		public String browsePilotScheduleDesc(@ModelAttribute SearchBookingForm searchBookingForm,Model model) {
		  	model.addAttribute("booking", repository.getSingleActiveBooking(searchBookingForm.getBookingId()));
	        
		  	model.addAttribute("currentPage", 1);
	    	model.addAttribute("totalPage", 1);
	    	model.addAttribute("totalItem", 1);
		  	
		  	model.addAttribute("bookingSearchForm",searchBookingForm); 
	  
	        return "indexBooking";

	  }
	  
	  
	  public Page<Booking> findBookingPage(int pageNumber){
		  Pageable pageable= PageRequest.of(pageNumber-1,8);
		  
		  return repository.findAll(pageable);
	  }
	 
}
