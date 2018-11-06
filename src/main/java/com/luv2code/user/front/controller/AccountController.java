package com.luv2code.user.front.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.user.front.domain.PrimaryAccount;
import com.luv2code.user.front.domain.PrimaryTransaction;
import com.luv2code.user.front.domain.SavingsAccount;
import com.luv2code.user.front.domain.SavingsTransaction;
import com.luv2code.user.front.domain.User;
import com.luv2code.user.front.service.AccountService;
import com.luv2code.user.front.service.TransactionalService;
import com.luv2code.user.front.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionalService transactionService;
	
	@GetMapping("/primaryAccount")
	 public String PrimaryAccount(Model model , Principal principal) {
		List<PrimaryTransaction> primaryTransactionList = transactionService.findPrimaryTransactionalList(principal.getName());  
		
		 User user = userService.findByUsername(principal.getName());
		 PrimaryAccount primaryAccount = user.getPrimaryAccount();
		 
		 model.addAttribute("primaryAccount" , primaryAccount);
		 model.addAttribute("primaryTransactionList" , primaryTransactionList);
		 
		 return "primaryAccount";
	 }
	
	
	@GetMapping("/savingsAccount")
	 public String SavingAccount(Model model , Principal principal) {
		List<SavingsTransaction> savingsTransactionList = transactionService.findSavingsTransactionList(principal.getName());  

		 User user = userService.findByUsername(principal.getName());
		 SavingsAccount savingsAccount = user.getSavingAccount();
		 
		 model.addAttribute("savingsAccount" , savingsAccount);
		 model.addAttribute("savingsTransactionList" , savingsTransactionList);
		 return "savingsAccount";
	 }
	
	@GetMapping("/deposit")
	public String Deposit(Model model) {
		model.addAttribute("accountType" , "");
		model.addAttribute("amount" , "");
		
		return "deposit";
	}
	
	@PostMapping("/deposit")
	public String DepositPost(@ModelAttribute("accountType") String accountType ,
			 @ModelAttribute("amount") String amount , Principal principal) {
		accountService.deposit(accountType , Double.parseDouble(amount) , principal);
		
		return "redirect:/userFront";
	}
	
	@GetMapping("/withdraw")
	public String WithDraw(Model model) {
		model.addAttribute("accountType" , "");
		model.addAttribute("amount" , "");
		
		return "withdraw";
	}
	
	 @PostMapping("/withdraw")
	public String WithDraw(@ModelAttribute("accountType") String accountType ,
			 @ModelAttribute("amount") String amount , Principal principal) {
		accountService.withdraw(accountType , Double.parseDouble(amount) , principal);
		
		return "redirect:/userFront";
	}
	

}
