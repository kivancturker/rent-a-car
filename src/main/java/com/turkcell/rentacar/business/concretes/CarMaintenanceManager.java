package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.carmaintenances.GetByCarIdCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.carmaintenances.GetByIdCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.carmaintenances.ListCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.rentals.GetByCarIdRentalDto;
import com.turkcell.rentacar.business.exceptions.BusinessException;
import com.turkcell.rentacar.business.requests.carmaintenances.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carmaintenances.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.utils.constants.Messages;
import com.turkcell.rentacar.core.utils.helpers.IdValidationUtils;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.CarMaintenanceDao;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private RentalService rentalService;
	
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, 
			ModelMapperService modelMapperService,
			CarService carService,
			@Lazy RentalService rentalService) {
		
		super();
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.rentalService = rentalService;
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
		
		
		isCarExist(createCarMaintenanceRequest.getCarId());
		
		isCarRented(createCarMaintenanceRequest.getCarId());
		
		// Mapper doesn't work properly for CarMaintenance
		// It confused with Car class
		// So I will manually set the values
		
		CarMaintenance carMaintenance = new CarMaintenance();
		
		carMaintenance.setDescription(createCarMaintenanceRequest.getDescription());
		carMaintenance.setCar(new Car());
		carMaintenance.getCar().setId(createCarMaintenanceRequest.getCarId());
		
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.CAR_MAINTENANCE_ADD);
	}

	@Override
	public DataResult<GetByIdCarMaintenanceDto> getById(int id) {
		
		IdValidationUtils.checkIfIdValid(id, this.carMaintenanceDao);
		var result = this.carMaintenanceDao.getById(id);
		GetByIdCarMaintenanceDto response = this.modelMapperService.forDto()
				.map(result, GetByIdCarMaintenanceDto.class);
		
		return new SuccessDataResult<GetByIdCarMaintenanceDto>(response);
	}

	@Override
	public Result update(int id, 
			UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		
		IdValidationUtils.checkIfIdValid(id, this.carMaintenanceDao);
		CarMaintenance carMaintenance = this.modelMapperService.forRequest()
				.map(updateCarMaintenanceRequest, CarMaintenance.class);
		CarMaintenance carMaintenanceToUpdate = this.carMaintenanceDao.getById(id);
		carMaintenanceToUpdate.setReturnDate(carMaintenance.getReturnDate());
		
		this.carMaintenanceDao.save(carMaintenanceToUpdate);
		return new SuccessResult(Messages.CAR_MAINTENANCE_UPDATE);
	}

	@Override
	public Result delete(int id) {
		
		IdValidationUtils.checkIfIdValid(id, this.carMaintenanceDao);
		this.carMaintenanceDao.deleteById(id);
		return new SuccessResult(Messages.CAR_MAINTENANCE_DELETE);
	}
	
	
	@Override
	public DataResult<List<GetByCarIdCarMaintenanceDto>> getByCarId(int carId) {
		
		isCarExist(carId);
		
		var result = this.carMaintenanceDao.findByCarId(carId);
		
		List<GetByCarIdCarMaintenanceDto> response = result.stream()
		.map(carMaintenance -> this.modelMapperService.forDto()
		.map(carMaintenance, GetByCarIdCarMaintenanceDto.class))
		.collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetByCarIdCarMaintenanceDto>>(response);
	}

	private void isCarExist(int id) {
		
		if ( !carService.getById(id).isSuccess() )
		{
			throw new BusinessException(Messages.CAR_MAINTENANCE_CAR_NOT_EXIST);
		}
	}
	
	private void isCarRented(int rentalId) {
		
		List<GetByCarIdRentalDto> rentalDto = this.rentalService
				.getByCarId(rentalId).getData();
		
		
		for (GetByCarIdRentalDto rent : rentalDto)
		{
			if (Objects.isNull(rent.getReturnDate()))
			{
				throw new BusinessException(Messages.CAR_MAINTENANCE_CAR_RENTED);
			}
		}
	}
	
	
}
