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
@Table(name="saving_account")
public class SavingsAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="account_number")
	private int accountNumber;
	
	@Column(name="account_balance")
	private BigDecimal accountBalance;
	
	@OneToOne(mappedBy = "savingAccount" , cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
	private User user;
	
	@OneToMany(mappedBy = "savingAccount" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private List<SavingsTransaction> savingTransactionList;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<SavingsTransaction> getSavingTransactionList() {
		return savingTransactionList;
	}

	public void setSavingTransactionList(List<SavingsTransaction> savingTransactionList) {
		this.savingTransactionList = savingTransactionList;
	}
	
	
	

}
