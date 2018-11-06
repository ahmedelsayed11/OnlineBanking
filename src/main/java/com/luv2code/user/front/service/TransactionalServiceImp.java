package com.luv2code.user.front.service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.user.front.Dao.PrimaryAccountDao;
import com.luv2code.user.front.Dao.PrimaryTransactionDao;
import com.luv2code.user.front.Dao.RecipientDao;
import com.luv2code.user.front.Dao.SavingTransactionDao;
import com.luv2code.user.front.Dao.SavingsAccountDao;
import com.luv2code.user.front.domain.PrimaryAccount;
import com.luv2code.user.front.domain.PrimaryTransaction;
import com.luv2code.user.front.domain.Recipient;
import com.luv2code.user.front.domain.SavingsAccount;
import com.luv2code.user.front.domain.SavingsTransaction;
import com.luv2code.user.front.domain.User;


@Service
public class TransactionalServiceImp implements TransactionalService {

	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	  private PrimaryTransactionDao primaryTransactionDao;
	 
	 @Autowired
	 private SavingTransactionDao savingTransactionDao;
	 
	 @Autowired
	 private PrimaryAccountDao primaryAccountDao;
	 
	 @Autowired
	 private SavingsAccountDao  savingAccountDao;
	 
	 @Autowired 
	 private RecipientDao recipientDao;
	 

	@Override
	public List<PrimaryTransaction> findPrimaryTransactionalList(String username) {
		
		User user = userService.findByUsername(username);
		List<PrimaryTransaction> primaryTransactionList = user.getPrimaryAccount().getPrimaryTransaction();
	 
		return  primaryTransactionList;
	}

	@Override
	public List<SavingsTransaction> findSavingsTransactionList(String username) {
		User user = userService.findByUsername(username);
		List<SavingsTransaction> savingTransactionList = user.getSavingAccount().getSavingTransactionList();
		
		return savingTransactionList;
	}

	@Override
	public void saveDepositPrimaryTransaction(PrimaryTransaction primaryTransaction) {
		 
		primaryTransactionDao.save(primaryTransaction);
		
	}

	@Override
	public void saveDepositSavingsTransaction(SavingsTransaction savingsTransaction) {
		savingTransactionDao.save(savingsTransaction);
	}

	@Override
	public void betweenAccountsTransfer(String transferFrom, String transferTo, double amount,
			PrimaryAccount primaryAccount, SavingsAccount savingsAccount) {
		
		if(transferFrom.equalsIgnoreCase("primary") && transferTo.equalsIgnoreCase("savings")) {
			
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
			
			primaryAccountDao.save(primaryAccount);
			savingAccountDao.save(savingsAccount);
			
			Date date = new Date();
			 
			PrimaryTransaction primaryTransaction = new PrimaryTransaction
					 ("primary" , date ,"account", "Between Accounts Transfer from primaryAccount" 
							 , "finished" , amount , primaryAccount.getAccountBalance() , primaryAccount );		
			
			primaryTransactionDao.save(primaryTransaction);
		}else if(transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("primary")) {
			
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
			
			savingAccountDao.save(savingsAccount);
			primaryAccountDao.save(primaryAccount);
			
			Date date = new Date();
			
			SavingsTransaction savingTransaction = new SavingsTransaction
					  ("savings" , date ,"account", "Between Accounts Transfer from savingAccount " 
						 , "finished" , amount , savingsAccount.getAccountBalance() , savingsAccount);		
			
			savingTransactionDao.save(savingTransaction);
		}
		
		
	}

	@Override
	public Recipient findRecipientByName(String recipientName) {
		  
		return recipientDao.findByName(recipientName);
	}

	@Override
	public List<Recipient> findRecepientList(Principal principal) {
		String username = principal.getName();
		  List<Recipient> recipientList =  recipientDao.findAll().stream().
				   filter(recipient -> username.equals(recipient.getTheUser().getUsername())).
				    collect(Collectors.toList());
		         
		 
		return recipientList;
	}

	@Override
	@Transactional
	public void saveRecipient(Recipient recipient) {
		
		recipientDao.save(recipient);
		
	}

	@Override
	@Transactional
	public void deleteRecipientByName(String recipientName) {
       
		recipientDao.deleteByName(recipientName);
	}

	@Override
	@Transactional
	public void toSomeoneElse(Recipient recipient, String accountType, double amount, PrimaryAccount primaryAccount,
			SavingsAccount savingAccount) {

		if(accountType.equalsIgnoreCase("primary")) {
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			
			primaryAccountDao.save(primaryAccount);
			
			Date date = new Date();
			
			PrimaryTransaction primaryTransaction = new PrimaryTransaction
					("primary" , date ,"account", "Transfer From primaryAccount To Recipient  " + recipient.getName() 
					 , "finished" , amount , primaryAccount.getAccountBalance() , primaryAccount);
			
			primaryTransactionDao.save(primaryTransaction);
		}else if(accountType.equalsIgnoreCase("Savings")) {
			savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingAccountDao.save(savingAccount);
			
			Date date = new Date();
			
			SavingsTransaction savingTransaction = new SavingsTransaction(
					"savings" , date ,"account", "Transfer from savingAccount  to Recipient  " + recipient.getName() 
					 , "finished" , amount , savingAccount.getAccountBalance() , savingAccount);
			savingTransactionDao.save(savingTransaction);
		}
		
	}
	 
	 
	
}
