package com.luv2code.user.front.service;

import java.util.List;

import com.luv2code.user.front.domain.Appointment;

public interface AppointmentService {
 
	 Appointment createAppointment(Appointment appointment);
	 
	 List<Appointment> findAll();
	 
	 Appointment findAppointment(Long id);
	 
	 void confirmAppointment(Long id);
}
