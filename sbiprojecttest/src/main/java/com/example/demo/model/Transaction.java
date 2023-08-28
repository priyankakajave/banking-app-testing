package com.example.demo.model;

import java.sql.Date;

// import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")

public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionID;
	@Column(name="senderAccNo")
	private long senderAccNo;
	@Column(name="receiverAccNo")
	private long receiverAccNo;
	@Column(name="amount")
	private double amount;
	@Column(name="TransactionDetails")
	private String transactionDetails;
	@Column(name="transDate")
	private Date transDate;
	
	public Transaction() {
		
	}
	
	public Transaction(long senderAccNo, long receiverAccNo, double amount, String transactionDetails,
			Date transDate) {
		super();
		this.senderAccNo = senderAccNo;
		this.receiverAccNo = receiverAccNo;
		this.amount = amount;
		this.transactionDetails = transactionDetails;
		this.transDate=transDate;
		
	}
	
	public long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}
	public long getSenderAccNo() {
		return senderAccNo;
	}
	public void setSenderAccNo(long senderAccNo) {
		this.senderAccNo = senderAccNo;
	}
	public long getReceiverAccNo() {
		return receiverAccNo;
	}
	public void setReceiverAccNo(long receiverAccNo) {
		this.receiverAccNo = receiverAccNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTransactionDetails() {
		return transactionDetails;
	}
	public void setTransactionDetails(String transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate =transDate;
		
	}
	
	

}
