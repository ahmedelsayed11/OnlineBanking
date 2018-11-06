package com.luv2code.user.front.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.user.front.domain.Appointment;
import com.luv2code.user.front.domain.User;
import com.luv2code.user.front.service.AppointmentService;
import com.luv2code.user.front.service.UserService;

//import groovyjarjarcommonscli.ParseException;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping("/create")
	public String  Appointment(Model model) {
		
		Appointment appointment = new Appointment();
		
		model.addAttribute("appointment" , appointment);
		model.addAttribute("dateString" , "");
		
		return "appointment";
	}
	
	@PostMapping("/create")
	public String createAppointment(@ModelAttribute("appointment") Appointment appointment , 
			@ModelAttribute("dateString") String date , Model model , Principal principal) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date1;
		try {
			date1 = format.parse(date);
			
			appointment.setDate(date1);

		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User user = userService.findByUsername(principal.getName());
		appointment.setTheUser(user);
		
		appointmentService.createAppointment(appointment);
		
		return "redirect:/userFront";
	}

}
