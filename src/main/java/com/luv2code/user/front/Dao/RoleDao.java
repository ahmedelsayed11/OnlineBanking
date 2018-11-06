package com.luv2code.user.front.Dao;

import org.springframework.data.repository.CrudRepository;

import com.luv2code.user.front.domain.security.entity.Role;

public interface RoleDao  extends CrudRepository<Role, Integer>{
	
	 Role findByName(String name);

}
