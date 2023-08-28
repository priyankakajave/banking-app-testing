package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Beneficiary;
import com.example.demo.model.Customer;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary,String> {

	Optional<Beneficiary> findById(String userID);
//	public Customer findByCustomerID(String CustomerID);
}
