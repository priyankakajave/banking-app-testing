package com.example.demo.model;

// import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Credentials")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="customerID", nullable=false)
	private String customerID;
	
	@Column(name="password", nullable=false)
	private String password;

	@Column(name="transactionPassword", nullable=false)
	private String transactionPassword;
	
	//Default constructor
	public Customer() {
		
	}
	
	//Parameterised
	

	public String getCustomerID() {
		return customerID;
	}

	public Long getID() {
		return id;
	}

	public Customer(long id, String password, String CustomerID, String TransactionPassword) {
		super();		
		this.password = password;
		this.transactionPassword = TransactionPassword;
		this.id = id;
		this.customerID = CustomerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public void setID(Long ID) {
		id = ID;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTransactionPassword() {
		return transactionPassword;
	}

	public void setTransactionPassword(String Transactionpassword) {
		transactionPassword = Transactionpassword;
	}
	
	//getter-setter
}
