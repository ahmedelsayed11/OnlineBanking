package com.luv2code.user.front.service;

import java.security.Principal;

import com.luv2code.user.front.domain.PrimaryAccount;
import com.luv2code.user.front.domain.SavingsAccount;

public interface AccountService {
	
	PrimaryAccount createPrimaryAccount();
	SavingsAccount createSavingAccount();
	void deposit(String accountType, double parseDouble, Principal principal);
	void withdraw(String accountType, double parseDouble, Principal principal);
	
}
