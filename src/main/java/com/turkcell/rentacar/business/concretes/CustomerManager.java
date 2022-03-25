package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.customers.GetByIdCustomerDto;
import com.turkcell.rentacar.business.dtos.customers.ListCustomerDto;
import com.turkcell.rentacar.business.requests.customers.CreateCustomerRequest;
import com.turkcell.rentacar.business.requests.customers.UpdateCustomerRequest;
import com.turkcell.rentacar.core.utils.constants.Messages;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.CustomerDao;
import com.turkcell.rentacar.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService {
	
	private CustomerDao customerDao;
	private ModelMapperService modelMapperService;
	
	
	@Autowired
	public CustomerManager(CustomerDao customerDao, ModelMapperService modelMapperService) {
		this.customerDao = customerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListCustomerDto>> getAll() {
		var result = this.customerDao.findAll();
		List<ListCustomerDto> response = result.stream()
				.map(customer -> this.modelMapperService.forDto()
				.map(customer, ListCustomerDto.class))
				.collect(Collectors.toList());
				
		return new SuccessDataResult<List<ListCustomerDto>>(response);
	}

	@Override
	public DataResult<GetByIdCustomerDto> getById(int id) {
		var result = this.customerDao.getById(id);
		GetByIdCustomerDto response = this.modelMapperService.forDto().map(result, GetByIdCustomerDto.class);
		
		return new SuccessDataResult<GetByIdCustomerDto>(response);
	}

	@Override
	public Result add(CreateCustomerRequest createCustomerRequest) {
		Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
		this.customerDao.save(customer);
		return new SuccessResult(Messages.CUSTOMER_ADD);
	}

	@Override
	public Result update(int id, UpdateCustomerRequest updateCustomerRequest) {
		Customer customer = this.modelMapperService.forRequest().map(updateCustomerRequest, Customer.class);
		Customer updated = this.customerDao.getById(id);
		updated.setRentals(customer.getRentals());
		
		this.customerDao.save(updated);
		return new SuccessResult(Messages.CUSTOMER_UPDATE);
	}

	@Override
	public Result delete(int id) {
		this.customerDao.deleteById(id);
		return new SuccessResult(Messages.CUSTOMER_DELETE);
	}

}
