package com.luv2code.user.front.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.luv2code.user.front.domain.Appointment;

public interface AppointmentDao extends JpaRepository<Appointment, Long> {
       
	  List<Appointment> findAll();
	  
	  
}
