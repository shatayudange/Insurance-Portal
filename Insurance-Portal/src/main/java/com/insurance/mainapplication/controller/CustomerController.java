package com.insurance.mainapplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.mainapplication.exception.ResourceNotFoundException;
import com.insurance.mainapplication.model.Customer;
import com.insurance.mainapplication.repository.CustomerRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomer(){
		return customerRepository.findAll();
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(value ="id") Long customerId) throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException ("No Customer found for this ID"));
		return ResponseEntity.ok().body(customer);
	}
	
	@PostMapping("/customers")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value="id") Long customerId,
			@Valid @RequestBody Customer customerdetails) throws ResourceNotFoundException{
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException ("No Customer found for this ID"));
		
		customer.setEmailId(customerdetails.getEmailId());
		customer.setFirstName(customerdetails.getFirstName());
		customer.setLastName(customerdetails.getLastName());
		
		final Customer updateCustomer = customerRepository.save(customer);
		return ResponseEntity.ok(updateCustomer);
			}
	
	@DeleteMapping("/customers/{id}")
	public Map<String,Boolean> deleteCustomer(@PathVariable(value="id") Long customerId) throws ResourceNotFoundException{
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException ("No Customer found for this ID"));
		
		customerRepository.delete(customer);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
		
	}
}
