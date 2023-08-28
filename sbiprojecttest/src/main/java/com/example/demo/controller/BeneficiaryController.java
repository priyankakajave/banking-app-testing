package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Beneficiary;
import com.example.demo.repository.BeneficiaryRepository;

@RestController
@RequestMapping("/api/v1/")
public class BeneficiaryController {

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;

	// get all beneficiaries

	@GetMapping("/beneficiaries")
	public List<Beneficiary> getAllBeneficiarys() {
		return beneficiaryRepository.findAll();
	}

	@PostMapping("/sendBeneficiary")
	public Beneficiary createBeneficiary(@Validated @RequestBody Beneficiary newBeneficiary) {
		return beneficiaryRepository.save(newBeneficiary);
	}

	@PutMapping("/updateBeneficiary/{id}")
	public ResponseEntity<Beneficiary> updateBeneficiary(@PathVariable(value = "id") String userID,
			@Validated @RequestBody Beneficiary newBeneficiary) throws ResourceNotFoundException {
		Beneficiary updatedBeneficiary = beneficiaryRepository.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("Beneficiary is not avaiable:" + userID));
		updatedBeneficiary.setAccountNo(newBeneficiary.getAccountNo());
		updatedBeneficiary.setNickname(newBeneficiary.getNickname());
		updatedBeneficiary.setName(newBeneficiary.getName());
		beneficiaryRepository.save(updatedBeneficiary);

		return ResponseEntity.ok(updatedBeneficiary);
	}

	@DeleteMapping("/deleteBeneficiary/{id}")
	public Map<String, Boolean> deleteBeneficiary(@PathVariable(value = "id") String userID)
			throws ResourceNotFoundException {
		Beneficiary updatedBeneficiary = beneficiaryRepository.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("Beneficiary is not Available:" + userID));
		beneficiaryRepository.delete(updatedBeneficiary);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Beneficiary has been Deleted", Boolean.TRUE);
		return response;
	}

}