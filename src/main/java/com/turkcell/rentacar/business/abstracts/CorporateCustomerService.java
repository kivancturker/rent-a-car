package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.corporatecustomers.GetByIdCorporateCustomerDto;
import com.turkcell.rentacar.business.dtos.corporatecustomers.ListCorporateCustomerDto;
import com.turkcell.rentacar.business.requests.corporatecustomers.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.corporatecustomers.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

public interface CorporateCustomerService {
	DataResult<List<ListCorporateCustomerDto>> getAll();
	Result  add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	DataResult<GetByIdCorporateCustomerDto> getById(int id);
	Result  update(int id, UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
	Result  delete(int id);
}
