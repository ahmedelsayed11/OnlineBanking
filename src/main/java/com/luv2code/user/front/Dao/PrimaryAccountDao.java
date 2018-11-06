package com.luv2code.user.front.Dao;

import org.springframework.data.repository.CrudRepository;

import com.luv2code.user.front.domain.PrimaryAccount;


public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long> {
      PrimaryAccount findByAccountNumber(int accountNumber);
	 
}
