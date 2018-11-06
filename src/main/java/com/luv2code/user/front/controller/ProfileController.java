package com.luv2code.user.front.controller;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.user.front.domain.User;
import com.luv2code.user.front.service.UserService;

@Controller
@RequestMapping("/user")
public class ProfileController {
	
	@Autowired
	private UserService userService;
	 
	
	
	
	@GetMapping("/profile")
	public String GetProfile( Model model , Principal principal) {
		User user =  userService.findByUsername(principal.getName());
		model.addAttribute("user" , user);
		 
		return "profile";
	}
	
	@PostMapping("/profile")
	public String PostProfile(@ModelAttribute("user") User user , Model model  ) {
		  
		 User theUser = userService.findByUsername(user.getUsername());
		   theUser.setUsername(user.getUsername());
		   theUser.setEmail(user.getEmail());
		   theUser.setFirstName(user.getFirstName());
		   theUser.setLastName(user.getLastName());
		   theUser.setPhone(user.getPhone());
		   
		  model.addAttribute("user" , theUser);
		  
		  userService.saveUser(theUser);
		   
		return "profile";
			}

	
	
}
