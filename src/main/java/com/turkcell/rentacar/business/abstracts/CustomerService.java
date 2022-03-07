package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.customers.GetByIdCustomerDto;
import com.turkcell.rentacar.business.dtos.customers.ListCustomerDto;
import com.turkcell.rentacar.business.requests.customer.CreateCustomerRequest;
import com.turkcell.rentacar.business.requests.customer.UpdateCustomerRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

public interface CustomerService {
	DataResult<List<ListCustomerDto>> getAll();
	DataResult<GetByIdCustomerDto> getById(int id);
	Result add(CreateCustomerRequest createCustomerRequest);
	Result update(int id, UpdateCustomerRequest updateCustomerRequest);
	Result delete(int id);
}
