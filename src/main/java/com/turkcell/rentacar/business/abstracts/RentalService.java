package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.rentals.GetByIdRentalDto;
import com.turkcell.rentacar.business.dtos.rentals.ListRentalDto;
import com.turkcell.rentacar.business.requests.rentals.CreateRentalRequest;
import com.turkcell.rentacar.business.requests.rentals.UpdateRentalRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

public interface RentalService {
	DataResult<List<ListRentalDto>> getAll();
	DataResult<GetByIdRentalDto> getById(int id);
	Result add(CreateRentalRequest createRentalRequest);
	Result update(int id, UpdateRentalRequest updateRentalRequest);
	Result delete(int id);
}
