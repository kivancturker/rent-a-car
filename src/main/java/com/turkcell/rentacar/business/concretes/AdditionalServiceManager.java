package com.turkcell.rentacar.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.dtos.additionalservices.GetByIdAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.additionalservices.ListAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.additionalservices.CreateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.additionalservices.UpdateAdditionalServiceRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<GetByIdAdditionalServiceDto> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result update(int id, UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
