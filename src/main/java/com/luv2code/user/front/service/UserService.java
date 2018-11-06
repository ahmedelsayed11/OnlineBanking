package com.luv2code.user.front.service;

import java.util.Set;

import com.luv2code.user.front.domain.User;
import com.luv2code.user.front.domain.security.entity.UserRole;

public interface UserService {
	
	//methods to implements in user Service implementation
	
	 User findByUsername(String username);
	 User findByEmail(String email);
	 
	 
	 boolean checkUserExists(String username , String email);
	 
	 boolean checkUsernameExists(String username);
	 
	 boolean checkEmailExists(String email);
	 
	 User saveUser(User user);
	 
	 User createUser(User user , Set<UserRole> userRole);
	 
	 
	 

}
