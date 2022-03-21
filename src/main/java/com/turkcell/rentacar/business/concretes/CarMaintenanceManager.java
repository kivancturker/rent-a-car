package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.dtos.GetByIdCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.ListCarMaintenanceDto;
import com.turkcell.rentacar.business.requests.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.utils.constants.Messages;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.CarMaintenanceDao;
import com.turkcell.rentacar.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService) {
		super();
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		var result = this.carMaintenanceDao.findAll();
		List<ListCarMaintenanceDto> response = result.stream()
		.map(carMaintenance -> this.modelMapperService.forDto()
		.map(carMaintenance, ListCarMaintenanceDto.class))
		.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response);
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		CarMaintenance carMaintenance  = this.modelMapperService.forRequest()
				.map(createCarMaintenanceRequest, CarMaintenance.class);
		carMaintenance.setId(0);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.CAR_MAINTENANCE_ADD);
	}

	@Override
	public DataResult<GetByIdCarMaintenanceDto> getById(int id) {
		var result = this.carMaintenanceDao.getById(id);
		GetByIdCarMaintenanceDto response = this.modelMapperService.forDto().map(result, GetByIdCarMaintenanceDto.class);
		
		return new SuccessDataResult<GetByIdCarMaintenanceDto>(response);
	}

	@Override
	public Result update(int id, UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
		CarMaintenance carMaintenanceToUpdate = this.carMaintenanceDao.getById(id);
		carMaintenanceToUpdate.setReturnDate(carMaintenance.getReturnDate());
		
		this.carMaintenanceDao.save(carMaintenanceToUpdate);
		return new SuccessResult(Messages.CAR_MAINTENANCE_UPDATE);
	}

	@Override
	public Result delete(int id) {
		this.carMaintenanceDao.deleteById(id);
		return new SuccessResult(Messages.CAR_MAINTENANCE_DELETE);
	}

}
