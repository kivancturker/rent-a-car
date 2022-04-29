package com.turkcell.rentacar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.dtos.additionalservices.GetByIdAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.additionalservices.ListAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.additionalservices.CreateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.additionalservices.UpdateAdditionalServiceRequest;
import com.turkcell.rentacar.core.utils.constants.Messages;
import com.turkcell.rentacar.core.utils.helpers.DtoUtils;
import com.turkcell.rentacar.core.utils.helpers.IdValidationUtils;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.AdditionalServiceDao;
import com.turkcell.rentacar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {
	
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
	
	
	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {
		
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getAll() {
		
		var result = this.additionalServiceDao.findAll();
		
		List<ListAdditionalServiceDto> 
				response = DtoUtils.listEntityToListDto(
						result, ListAdditionalServiceDto.class);
		
		
		return new SuccessDataResult<
				List<ListAdditionalServiceDto>>(response);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		
		AdditionalService request = this.modelMapperService.forRequest()
				.map(createAdditionalServiceRequest, 
						AdditionalService.class);
		this.additionalServiceDao.save(request);
		
		return new SuccessResult(Messages.ADDITIONAL_SERVICE_ADD);
	}

	@Override
	public DataResult<GetByIdAdditionalServiceDto> getById(int id) {
		
		IdValidationUtils.checkIfIdValid(id, this.additionalServiceDao);
		
		var result = this.additionalServiceDao.findById(id);
		GetByIdAdditionalServiceDto response = this.modelMapperService
				.forDto().map(result, GetByIdAdditionalServiceDto.class);
		
		return new SuccessDataResult<GetByIdAdditionalServiceDto>(response); 
	}

	@Override
	public Result update(int id, UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		
		IdValidationUtils.checkIfIdValid(id, this.additionalServiceDao);
		
		AdditionalService request = this.modelMapperService.forRequest()
				.map(updateAdditionalServiceRequest, 
						AdditionalService.class);
		AdditionalService updated = this.additionalServiceDao.getById(id);
		
		updated.setServiceName(request.getServiceName());
		updated.setDailyPrice(request.getDailyPrice());
		
		this.additionalServiceDao.save(updated);
		
		return new SuccessResult(Messages.ADDITIONAL_SERVICE_UPDATE);
	}

	@Override
	public Result delete(int id) {
		
		IdValidationUtils.checkIfIdValid(id, this.additionalServiceDao);
		
		this.additionalServiceDao.deleteById(id);
		return new SuccessResult(Messages.ADDITIONAL_SERVICE_DELETE);
	}

}
