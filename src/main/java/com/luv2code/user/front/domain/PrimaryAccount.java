package com.luv2code.user.front.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="primary_account")
public class PrimaryAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@Column(name="id" , nullable = false , updatable = false)
	private Long primaryId;
	
	@Column(name="account_number")
	private int accountNumber;
	
	@Column(name="account_balance")
	private BigDecimal accountBalance; 
	
	@OneToOne(mappedBy = "primaryAccount" , cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
	private User user;
	
	@OneToMany(mappedBy = "primaryAccount" , cascade = CascadeType.ALL , fetch = FetchType.LAZY )
	private List<PrimaryTransaction> primaryTransaction;
   
	
	
	
	public Long getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(Long primaryId) {
		this.primaryId = primaryId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public List<PrimaryTransaction> getPrimaryTransaction() {
		return primaryTransaction;
	}

	public void setPrimaryTransaction(List<PrimaryTransaction> primaryTransaction) {
		this.primaryTransaction = primaryTransaction;
	}

	
	
	
	
	

}
