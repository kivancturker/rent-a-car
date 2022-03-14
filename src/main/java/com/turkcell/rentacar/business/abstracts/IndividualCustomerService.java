package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.individualcustomer.GetByIdIndividualCustomerDto;
import com.turkcell.rentacar.business.dtos.individualcustomer.ListIndividualCustomerDto;
import com.turkcell.rentacar.business.requests.individualcustomer.CreateIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.individualcustomer.UpdateIndividualCustomerRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

public interface IndividualCustomerService {
	DataResult<List<ListIndividualCustomerDto>> getAll();
	Result  add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	DataResult<GetByIdIndividualCustomerDto> getById(int id);
	Result  update(int id, UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	Result  delete(int id);
}
