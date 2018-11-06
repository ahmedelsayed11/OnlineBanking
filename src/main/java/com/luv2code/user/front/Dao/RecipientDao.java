package com.luv2code.user.front.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.luv2code.user.front.domain.Recipient;

public interface RecipientDao  extends CrudRepository<Recipient, Long>{
     
	  List<Recipient> findAll();
	  
	  Recipient findByName(String recipient);
	  
	  void deleteByName(String recipientName);
}
