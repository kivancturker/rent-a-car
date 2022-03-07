package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.rentals.GetByIdRentalDto;
import com.turkcell.rentacar.business.dtos.rentals.ListRentalDto;
import com.turkcell.rentacar.business.requests.rentals.CreateRentalRequest;
import com.turkcell.rentacar.business.requests.rentals.UpdateRemtalRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

public interface RentalService {
	DataResult<List<ListRentalDto>> getAll();
	DataResult<GetByIdRentalDto> getById(int id);
	Result add(CreateRentalRequest createCustomerRequest);
	Result update(int id, UpdateRemtalRequest updateCustomerRequest);
	Result delete(int id);
}
