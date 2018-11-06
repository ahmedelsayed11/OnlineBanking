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
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.user.front.domain.PrimaryAccount;
import com.luv2code.user.front.domain.Recipient;
import com.luv2code.user.front.domain.SavingsAccount;
import com.luv2code.user.front.domain.User;
import com.luv2code.user.front.service.TransactionalService;
import com.luv2code.user.front.service.UserService;


@Controller
@RequestMapping("/transfer")
public class TransferAccountController {
	
	 @Autowired 
	  private UserService userService;
	 
	 @Autowired 
	  private TransactionalService transactionService;
	
	@GetMapping("/betweenAccounts")
	public String Transfer(Model model) {
		model.addAttribute("transferFrom" , "");
		model.addAttribute("transferTo" , "");
		model.addAttribute("amount" , "");

		return "betweenAccounts";
	}
	 
	 @PostMapping("/betweenAccounts")
	 public String TransferPost(
			 @ModelAttribute("transferFrom") String transferFrom ,
			 @ModelAttribute("transferTo") String transferTo,
			 @ModelAttribute("amount") double amount ,
			 Principal principal
			 ) throws Exception {
		 
		 User user = userService.findByUsername(principal.getName());
		 PrimaryAccount primaryAccount  = user.getPrimaryAccount();
		 SavingsAccount savingsAccount = user.getSavingAccount();
		 
		 transactionService.betweenAccountsTransfer( transferFrom ,  transferTo , 
				                          amount,  primaryAccount ,  savingsAccount );
		 
		 
		 return "redirect:/userFront";
		 
	 }
	 
	 @GetMapping("/recipient")
	 public String Recipent(Model model , Principal principal) {
		 
		 Recipient recipient = new Recipient();
		 
		 List<Recipient> recipientList = transactionService.findRecepientList(principal);
		 
		 model.addAttribute("recipient" , recipient);
		 model.addAttribute("recipientList" , recipientList);
		 
		 return "recipient";		 
	 }
	 
	 @PostMapping("/recipient/save")
	 public String RecipentSave(@ModelAttribute("recipient") Recipient recipient , Principal principal) {
		  
		  User user = userService.findByUsername(principal.getName());
		   recipient.setTheUser(user);
		  transactionService.saveRecipient(recipient);
		 
		 return"redirect:/transfer/recipient";
	 }
	 
	 @GetMapping("/recipient/edit")
	 public String RecipientEdit(@RequestParam("recipientName") String recipientName , 
			 Model model , Principal principal ) {
		 
		 Recipient recipient = transactionService.findRecipientByName(recipientName);
		 List<Recipient> recipientList = transactionService.findRecepientList(principal);
		 
		 model.addAttribute("recipient" , recipient);
		 model.addAttribute("recipietionList" , recipientList);
		 
		 return "recipient";
	 }
	 
	 
	 @GetMapping("/recipient/delete")
	 public String RecipientDelete(@RequestParam(value="recipientName") String recipientName ,
			 Model model , Principal principal) {
		 transactionService.deleteRecipientByName(recipientName);
		 
		 List< Recipient> recipientList = transactionService.findRecepientList(principal);
		 
		 Recipient recipient = new Recipient();
		 
		 model.addAttribute("recipient" , recipient);
		 model.addAttribute("recipientList" , recipientList);
		 
		 return "recipient";
	 }
	 
	 @GetMapping("/toSomeoneElse")
	 public String GettoSomeOneElsePage(Model model , Principal principal) {
		 List<Recipient> recipientList = transactionService.findRecepientList(principal);
			model.addAttribute("recipientList" , recipientList);
			model.addAttribute("accountType" , "");
		 
		 return "toSomeoneElse";
	 }
	  
	 
	 @PostMapping("/toSomeoneElse")
	  public String ToSomeOneElse(@ModelAttribute("recipientName") String recipientName ,
			   @ModelAttribute("accountType") String accountType ,@ModelAttribute("amount") double amount ,Principal principal) {
		 
		 User user = userService.findByUsername(principal.getName());
		 Recipient recipient = transactionService.findRecipientByName(recipientName);
		 
		 transactionService.toSomeoneElse(recipient , accountType , amount , user.getPrimaryAccount() , user.getSavingAccount());
		 
		 return "redirect:/userFront";
	 }

}
