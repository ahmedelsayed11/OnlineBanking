package com.luv2code.user.front.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="primary_transaction")
public class PrimaryTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="name")
	private String Name;
	
	@Column(name="description")
	private String descreption;
	
	@Column(name="status")
	private String Status;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="available_balance")
	private BigDecimal availableBalance;
	
	@ManyToOne
	@JoinColumn(name="primary_account_id")
	@JsonIgnore
	private PrimaryAccount primaryAccount;
	
	
	//default Constructor
 public PrimaryTransaction() {}
	public PrimaryTransaction(String type, Date date, String name, String descreption, String status, double amount,
			BigDecimal availableBalance , PrimaryAccount primaryAccount) {
		this.type = type;
		this.date = date;
		Name = name;
		this.descreption = descreption;
		Status = status;
		this.amount = amount;
		this.availableBalance = availableBalance;
		this.primaryAccount = primaryAccount;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescreption() {
		return descreption;
	}

	public void setDescreption(String descreption) {
		this.descreption = descreption;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public PrimaryAccount getPrimaryAccount() {
		return primaryAccount;
	}

	public void setPrimaryAccount(PrimaryAccount primaryAccount) {
		this.primaryAccount = primaryAccount;
	}
	
	
	
	

}
