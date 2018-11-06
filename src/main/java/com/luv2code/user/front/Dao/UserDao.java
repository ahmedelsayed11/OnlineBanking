package com.luv2code.user.front.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.user.front.domain.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
		
	//create a methods to implement in user service implementation
		User findByUsername(String userName);
		
		User findByEmail(String email);
		
}
