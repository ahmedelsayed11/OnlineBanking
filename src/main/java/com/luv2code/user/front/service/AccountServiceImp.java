package com.luv2code.user.front.service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.user.front.Dao.PrimaryAccountDao;
import com.luv2code.user.front.Dao.SavingsAccountDao;
import com.luv2code.user.front.domain.PrimaryAccount;
import com.luv2code.user.front.domain.PrimaryTransaction;
import com.luv2code.user.front.domain.SavingsAccount;
import com.luv2code.user.front.domain.SavingsTransaction;
import com.luv2code.user.front.domain.User;

@Service
public class AccountServiceImp implements AccountService {

	private static  int nextAccountNumber = 11223145;
	 
	@Autowired
	private PrimaryAccountDao  primaryAccountdao;
	
	@Autowired
	private SavingsAccountDao  savingAccountdao;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private TransactionalService transactionaService;
	
	
	@Override
	public PrimaryAccount createPrimaryAccount() {
		      PrimaryAccount primaryAccount = new PrimaryAccount();
		       primaryAccount.setAccountBalance(new BigDecimal(0.0));
		        primaryAccount.setAccountNumber(accountGen());
		
		        primaryAccountdao.save(primaryAccount);
		   
		  return       primaryAccountdao.findByAccountNumber(primaryAccount.getAccountNumber());
	}

	@Override
	public SavingsAccount createSavingAccount() {
		SavingsAccount savingsAccount = new SavingsAccount();
		 savingsAccount.setAccountBalance(new BigDecimal(0.0));
		savingsAccount.setAccountNumber(accountGen());
		
		savingAccountdao.save(savingsAccount);
		
	return savingAccountdao.findByAccountNumber(savingsAccount.getAccountNumber());
		
	}
	
	
	
	
	private int accountGen() {
		
		return ++nextAccountNumber;
		
	}

	@Override
	public void deposit(String accountType, double amount, Principal principal) {
		 User user = userService.findByUsername(principal.getName());
		 
		 if(accountType.equalsIgnoreCase("primary")) {
			 PrimaryAccount primaryAccount = user.getPrimaryAccount();
			 primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
			 
			 primaryAccountdao.save(primaryAccount);
			 
			 Date date = new Date();
			 
			
			PrimaryTransaction primaryTransaction = new PrimaryTransaction
					 ("primary" , date ,"account", "Deposit to primaryAccount" 
							 , "finished" , amount , primaryAccount.getAccountBalance() , primaryAccount );
			 transactionaService.saveDepositPrimaryTransaction(primaryTransaction);
			 
		 }else if(accountType.equalsIgnoreCase("Savings")) {
			 SavingsAccount savingAccount = user.getSavingAccount();
			  savingAccount.setAccountBalance(savingAccount.getAccountBalance().add(new BigDecimal(amount)));
			  savingAccountdao.save(savingAccount);
			  
			  Date date = new Date();
			  
			  
			SavingsTransaction savingTransaction = new SavingsTransaction
					  ("savings" , date ,"account", "Deposit to savingAccount" 
						 , "finished" , amount , savingAccount.getAccountBalance() ,savingAccount);
			
			transactionaService.saveDepositSavingsTransaction(savingTransaction);
		 }
		
	}

	@Override
	public void withdraw(String accountType, double amount, Principal principal) {
			User user = userService.findByUsername(principal.getName());
		if(accountType.equalsIgnoreCase("primary")) {
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
				primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountdao.save(primaryAccount);
			
			Date date = new Date();
			 
			PrimaryTransaction primaryTransaction = new PrimaryTransaction
					 ("primary" , date ,"account", "withdraw from primaryAccount" 
							 , "finished" , amount , primaryAccount.getAccountBalance() , primaryAccount );	
			  transactionaService.saveDepositPrimaryTransaction(primaryTransaction);
		}else if(accountType.equalsIgnoreCase("Savings")) {
			
			SavingsAccount savingAccount = user.getSavingAccount();
		 savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingAccountdao.save(savingAccount);
			Date date = new Date();
			 
			SavingsTransaction savingTransaction = new SavingsTransaction
					  ("savings" , date ,"account", "withdraw from savingAccount " 
						 , "finished" , amount , savingAccount.getAccountBalance() , savingAccount);
			transactionaService.saveDepositSavingsTransaction(savingTransaction);
		}
		
	}

}
