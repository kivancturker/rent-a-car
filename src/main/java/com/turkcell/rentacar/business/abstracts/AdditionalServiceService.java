package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.additionalservices.GetByIdAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.additionalservices.ListAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.additionalservices.CreateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.additionalservices.UpdateAdditionalServiceRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

public interface AdditionalServiceService {
	DataResult<List<ListAdditionalServiceDto>> getAll();
	Result  add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	DataResult<GetByIdAdditionalServiceDto> getById(int id);
	Result  update(int id, UpdateAdditionalServiceRequest updateAdditionalServiceRequest);
	Result  delete(int id);
}
