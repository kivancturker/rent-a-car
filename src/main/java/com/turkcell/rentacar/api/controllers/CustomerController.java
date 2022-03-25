package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.customers.GetByIdCustomerDto;
import com.turkcell.rentacar.business.dtos.customers.ListCustomerDto;
import com.turkcell.rentacar.business.requests.customers.CreateCustomerRequest;
import com.turkcell.rentacar.business.requests.customers.UpdateCustomerRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping
	public DataResult<List<ListCustomerDto>> getAll() {
		return this.customerService.getAll();
	}
	
	@GetMapping(path = "{id}")
	public DataResult<GetByIdCustomerDto> getById(@PathVariable(required=true, name="id") int id) {
		return this.customerService.getById(id);
	}
	
	@PostMapping
	public Result add(@RequestBody CreateCustomerRequest createCustomerRequest) {
		return this.customerService.add(createCustomerRequest);
	}
	
	@PutMapping(path = "{id}")
	public Result  update(@PathVariable(required = true, name="id") int id, 
				@RequestBody UpdateCustomerRequest updateCustomerRequest) {
		return this.customerService.update(id, updateCustomerRequest);
	}
	
	@DeleteMapping(path = "{id}")
	public Result  delete(@PathVariable(required = true, name="id") int id) {
		return this.customerService.delete(id);
	}
}
