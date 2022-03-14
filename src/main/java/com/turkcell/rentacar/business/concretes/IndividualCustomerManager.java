package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.IndividualCustomerService;
import com.turkcell.rentacar.business.dtos.individualcustomer.GetByIdIndividualCustomerDto;
import com.turkcell.rentacar.business.dtos.individualcustomer.ListIndividualCustomerDto;
import com.turkcell.rentacar.business.requests.individualcustomer.CreateIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.individualcustomer.UpdateIndividualCustomerRequest;
import com.turkcell.rentacar.core.utils.helpers.IdValidationUtils;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.IndividualCustomerDao;
import com.turkcell.rentacar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService) {
		super();
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
	}
	
	/*
	 * Todo: Check update part again.
	 */

	@Override
	public DataResult<List<ListIndividualCustomerDto>> getAll() {
		
		var result = this.individualCustomerDao.findAll();
		List<ListIndividualCustomerDto> response = result.stream()
		.map(ic -> this.modelMapperService.forDto()
		.map(ic, ListIndividualCustomerDto.class))
		.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListIndividualCustomerDto>>(response);
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult("IndividualCustomer.Add");
	}

	@Override
	public DataResult<GetByIdIndividualCustomerDto> getById(int id) {
		
		IdValidationUtils.checkIfIdValid(id, this.individualCustomerDao);
		var result = this.individualCustomerDao.getById(id);
		GetByIdIndividualCustomerDto response = this.modelMapperService.forDto().map(result, GetByIdIndividualCustomerDto.class);
		return new SuccessDataResult<GetByIdIndividualCustomerDto>(response);
	}

	@Override
	public Result update(int id, UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		
		IdValidationUtils.checkIfIdValid(id, this.individualCustomerDao);
		IndividualCustomer request = this.modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
		IndividualCustomer updated = this.individualCustomerDao.getById(id);
		
		
		this.individualCustomerDao.save(updated);
		return new SuccessResult("IndividualCustomer.Update");
	}

	@Override
	public Result delete(int id) {
		
		IdValidationUtils.checkIfIdValid(id, this.individualCustomerDao);
		this.individualCustomerDao.deleteById(id);
		return new SuccessResult("IndividualCustomer.Delete");
	}

}
