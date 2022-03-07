package com.turkcell.rentacar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.customers.GetByIdCustomerDto;
import com.turkcell.rentacar.business.dtos.customers.ListCustomerDto;
import com.turkcell.rentacar.business.requests.customer.CreateCustomerRequest;
import com.turkcell.rentacar.business.requests.customer.UpdateCustomerRequest;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.dataaccess.abstracts.CustomerDao;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<GetByIdCustomerDto> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(CreateCustomerRequest createCustomerRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result update(int id, UpdateCustomerRequest updateCustomerRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
