package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // get all customers

    @GetMapping("/users")
    public List<User> getAllCustomers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getACustomers(@PathVariable(value = "id") String userID) throws ResourceNotFoundException {
        return userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User is not Available:" + userID));
    }

    @GetMapping("/userid/{cid}")
    public User getACustomersbyCID(@PathVariable(value = "cid") String cid) throws ResourceNotFoundException {
        return userRepository.findByUserID(cid);
    }
    

    @PostMapping("/sendUser")
    public User createCustomer(@Validated @RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateCustomer(@PathVariable(value = "id") String userID,
            @Validated @RequestBody User newUser) throws ResourceNotFoundException {
        User updatedCustomer = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User is not avaiable:" + userID));
        updatedCustomer.setFirstName(newUser.getFirstName());
        updatedCustomer.setMiddleName(newUser.getMiddleName());
        updatedCustomer.setLastName(newUser.getLastName());
        updatedCustomer.setAccountNo(newUser.getAccountNo());
        updatedCustomer.setCurrentAddress(newUser.getCurrentAddress());
        updatedCustomer.setPermanentAddress(newUser.getPermanentAddress());
        updatedCustomer.setOccupation(newUser.getOccupation());
        updatedCustomer.setMinAccountBalance(newUser.getMinAccountBalance());
        updatedCustomer.setContactNo(newUser.getContactNo());
        updatedCustomer.setAadharNo(newUser.getAadharNo());
        updatedCustomer.setPanNo(newUser.getPanNo());
        updatedCustomer.setDOB(newUser.getDOB());
        userRepository.save(updatedCustomer);

        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/deleteUser/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") String userID)
            throws ResourceNotFoundException {
        User updatedCustomer = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User is not Available:" + userID));
        userRepository.delete(updatedCustomer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Customer has been Deleted", Boolean.TRUE);
        return response;
    }

}
