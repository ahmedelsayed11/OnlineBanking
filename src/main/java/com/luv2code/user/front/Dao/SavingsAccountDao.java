package com.luv2code.user.front.Dao;


import org.springframework.data.repository.CrudRepository;

import com.luv2code.user.front.domain.SavingsAccount;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {

		SavingsAccount findByAccountNumber(int accountNumber);
}
