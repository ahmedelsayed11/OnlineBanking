package com.luv2code.user.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.user.front.Dao.AppointmentDao;
import com.luv2code.user.front.domain.Appointment;

@Service
public class AppointmentServiceImp implements AppointmentService {
	
	@Autowired
	private AppointmentDao appointmentDao;

	@Override
	public Appointment createAppointment(Appointment appointment) {
		
		return appointmentDao.save(appointment);
 	}

	@Override
	public List<Appointment> findAll() {
		return appointmentDao.findAll();
	}

	@Override
	public Appointment findAppointment(Long id) {
		return  appointmentDao.getOne(id);
	}

	@Override
	public void confirmAppointment(Long id) {
            Appointment appointment = findAppointment(id);	
            appointment.setConfirmed(true);
            appointmentDao.save(appointment);
	}

	
	      
}
