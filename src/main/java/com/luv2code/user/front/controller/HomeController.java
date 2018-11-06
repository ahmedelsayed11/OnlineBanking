package com.luv2code.user.front.controller;



import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.luv2code.user.front.Dao.RoleDao;
import com.luv2code.user.front.domain.PrimaryAccount;
import com.luv2code.user.front.domain.SavingsAccount;
import com.luv2code.user.front.domain.User;
import com.luv2code.user.front.domain.security.entity.UserRole;
import com.luv2code.user.front.service.UserServiceImp;


@SuppressWarnings("deprecation")
@Controller
@ComponentScan("com.luv2code.user.front.service")
public class HomeController extends WebMvcConfigurerAdapter {
    
	 @Autowired
      private UserServiceImp UserService;
	 
	 @Autowired
	 private RoleDao roleDao;
	 
	 
	 @InitBinder
		public void initBinder(WebDataBinder dataBinder) {
			
			StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
			
			dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		}
	
	
	@RequestMapping("/")
	public String Home() {
		
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String index() {
		
		return "index";
		
	}
	
	//add controller for signUp page
	
	@GetMapping( "/signup" )
	public String signup( Model themodel) {
		//make an Object from User
		
		User user = new User();
		
		themodel.addAttribute("user" , user);
		
		return "signup";
	}
	
	@PostMapping( "/signup" )
	  public String signupPost(@Valid @ModelAttribute("user")  User user ,
			  Model model , BindingResult thebindResult ) {
		
		
	if(UserService.checkUserExists(user.getUsername() , user.getEmail() )) {
			
					if(UserService.checkEmailExists(user.getEmail())) 
				
			model.addAttribute("emailExists" , true);
			
			
		  if(UserService.checkUsernameExists(user.getUsername())) {
				
		   model.addAttribute("usernameExists" , true);
		   	
		 
		   }
	
	   
	 		return "signup";
		  
		}else {
			
			 Set<UserRole> userRole = new HashSet<>();
             userRole.add(new UserRole(user, roleDao.findByName("ROLE_USER")));
			   
		  UserService.createUser(user , userRole);
			
	      return "redirect:/";
	  }
	
	}
	
	 @GetMapping("/userFront")
	public String UserFront( Principal principal , Model model) {
		
		 User user = UserService.findByUsername(principal.getName());
	        PrimaryAccount primaryAccount = user.getPrimaryAccount();
	        SavingsAccount savingsAccount = user.getSavingAccount();

	        model.addAttribute("primaryAccount", primaryAccount);
	        model.addAttribute("savingsAccount", savingsAccount);
		 
		 
		return "userFront";
	}
	
}
	
	
		
  
	

	

	
	
	

