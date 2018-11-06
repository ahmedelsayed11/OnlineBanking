package com.luv2code.user.front.service;

import java.security.Principal;
import java.util.List;

import com.luv2code.user.front.domain.PrimaryAccount;
import com.luv2code.user.front.domain.PrimaryTransaction;
import com.luv2code.user.front.domain.Recipient;
import com.luv2code.user.front.domain.SavingsAccount;
import com.luv2code.user.front.domain.SavingsTransaction;

public interface TransactionalService {

	 List<PrimaryTransaction> findPrimaryTransactionalList(String username);
	 List<SavingsTransaction> findSavingsTransactionList(String username);
	 
	 void saveDepositPrimaryTransaction(PrimaryTransaction primaryTransaction);
	 void saveDepositSavingsTransaction(SavingsTransaction savingsTransaction);
	void betweenAccountsTransfer(String transferFrom, String transferTo, double amount, PrimaryAccount primaryAccount,
			SavingsAccount savingsAccount);
	
	Recipient findRecipientByName(String recipientName);
	List<Recipient> findRecepientList(Principal principal);
	void deleteRecipientByName(String recipientName);
	void saveRecipient(Recipient recipient);
	void toSomeoneElse(Recipient recipient, String accountType, double amount, PrimaryAccount primaryAccount,
			SavingsAccount savingAccount);

	 
}
