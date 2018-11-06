package com.luv2code.user.front.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.user.front.Dao.RoleDao;
import com.luv2code.user.front.Dao.UserDao;
import com.luv2code.user.front.domain.User;
import com.luv2code.user.front.domain.security.entity.UserRole;

@Service
@Component
public class UserServiceImp implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	
	@Autowired
	private UserDao userDao;
	
	//make injection for Role
	@Autowired
	private  RoleDao roleDao;
	
	
	//make injecjtion for passwordEncoder
	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 
	
	@Autowired
	 private AccountService accountService;
	

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.findByEmail(email);
	}

	@Override
	public boolean checkUserExists(String username, String email) {
		if(checkUsernameExists(username) || checkEmailExists(email)) {
			
			return true;
		}else
		return false;
	}

	@Override
	public boolean checkUsernameExists(String username) {
		if(null != findByUsername(username)) {
			
			return true;
		}else
			
		return false;
	}

	@Override
	public boolean checkEmailExists(String email) {
		if(null != findByEmail(email)) {
			return true;
			
		}else
			
		return false;
	}

	
	@Override
	 @Transactional
	public User createUser(User user, Set<UserRole> userRole) {
		User localUser = userDao.findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
        } else {
        	
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for (UserRole ur : userRole) {
                roleDao.save(ur.getUserRole());
            }

            user.getUserRole().addAll(userRole);  
            
            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingAccount(accountService.createSavingAccount());
           
            localUser = userDao.save(user);
        }

        return localUser;
		
	}

	@Override
	public User saveUser(User user) {
		return userDao.save(user);
	}

	
}
	 
	 
