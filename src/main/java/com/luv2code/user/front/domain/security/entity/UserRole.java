package com.luv2code.user.front.domain.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.luv2code.user.front.domain.User;

@Entity
@Table(name="user_role")
public class UserRole {
	
	  @Id
	  @GeneratedValue (strategy = GenerationType.AUTO)
	  @Column(name="id")
		private int id;
		
	  @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name="user_id")
		private User user;
		
	  @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name="role_id")
		private Role userRole;
		
		//default Constructor
		public UserRole() {}
		
		//constructor for user role
			
		public UserRole(User user , Role role) {
			
			this.user = user;
			this.userRole = role;
		}

		
		//Getter and setter 
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Role getUserRole() {
			return userRole;
		}

		public void setUserRole(Role userRole) {
			this.userRole = userRole;
		}
		
		
		
		
}
