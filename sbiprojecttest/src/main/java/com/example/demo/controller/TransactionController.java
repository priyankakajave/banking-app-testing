package com.example.demo.controller;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

	@Autowired
	private TransactionRepository transactionRepository;
	
	//Get all transactions
	
	@GetMapping("/transactions")
	public List<Transaction> getAllTransactions(){
		
		return transactionRepository.findAll();
	}
	
	@PostMapping("/sendTransaction")
	public Transaction createTransaction(@Validated @RequestBody Transaction newTransaction) {
		return transactionRepository.save(newTransaction);
		
	}
	
	@PutMapping("/updateTransaction/{id}")
	public ResponseEntity<Transaction> updateTransaction(@PathVariable(value="id") Long userId, @Validated @RequestBody Transaction newTransaction) throws ResourceNotFoundException
	{
	Transaction updatedTransaction = transactionRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Transaction Not Available :"+ userId));
	
	updatedTransaction.setSenderAccNo(newTransaction.getSenderAccNo());
	updatedTransaction.setReceiverAccNo(newTransaction.getReceiverAccNo());
	updatedTransaction.setAmount(newTransaction.getAmount());
	updatedTransaction.setTransactionDetails(newTransaction.getTransactionDetails());
	updatedTransaction.setTransDate(newTransaction.getTransDate());
	transactionRepository.save(updatedTransaction);
return ResponseEntity.ok(updatedTransaction);

	}
	
		
	
	@DeleteMapping("/deleteTransaction/{id}")
public Map<String, Boolean> deleteTransaction(@PathVariable(value="id") Long userId) throws ResourceNotFoundException{
		Transaction updatedTransaction = transactionRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Transaction Not Available :"+ userId));
		transactionRepository.delete(updatedTransaction);
		Map<String,Boolean> response =new HashMap<>();
		response.put("Transaction deleted successfully.", Boolean.TRUE);
	return response;
		
	}
	
}
