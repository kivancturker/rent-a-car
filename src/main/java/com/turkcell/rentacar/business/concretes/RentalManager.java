package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.carmaintenances.GetByCarIdCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.rentals.GetByCarIdRentalDto;
import com.turkcell.rentacar.business.dtos.rentals.GetByIdRentalDto;
import com.turkcell.rentacar.business.dtos.rentals.ListRentalDto;
import com.turkcell.rentacar.business.exceptions.BusinessException;
import com.turkcell.rentacar.business.requests.rentals.CreateRentalRequest;
import com.turkcell.rentacar.business.requests.rentals.UpdateRentalRequest;
import com.turkcell.rentacar.core.utils.constants.Messages;
import com.turkcell.rentacar.core.utils.helpers.IdValidationUtils;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.RentalDao;
import com.turkcell.rentacar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {
	
	/* 
	 * Todo: Apply the constraints.
	 */
	
	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CarMaintenanceService carMaintenanceService;
	
	@Autowired
	public RentalManager(RentalDao rentalDao, 
			ModelMapperService modelMapperService,
			CarMaintenanceService carMaintenanceService) {
		
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carMaintenanceService = carMaintenanceService;
	}

	@Override
	public DataResult<List<ListRentalDto>> getAll() {
		var result = this.rentalDao.findAll();
		List<ListRentalDto> response = result.stream()
				.map(rental -> this.modelMapperService.forDto()
				.map(rental, ListRentalDto.class))
				.collect(Collectors.toList());
				
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	@Override
	public DataResult<GetByIdRentalDto> getById(int id) {
		
		IdValidationUtils.checkIfIdValid(id, this.rentalDao);
		var result = this.rentalDao.findById(id);
		GetByIdRentalDto response = this.modelMapperService.forDto()
				.map(result, GetByIdRentalDto.class);
		
		return new SuccessDataResult<GetByIdRentalDto>(response); 
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		
		Rental request = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		
		isCarInMaintenance(request.getCar().getId());
		
		this.rentalDao.save(request);
		
		return new SuccessResult(Messages.RENTAL_ADD);
	}

	@Override
	public Result update(int id, UpdateRentalRequest updateRentalRequest) {
		
		IdValidationUtils.checkIfIdValid(id, this.rentalDao);
		Rental request = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		Rental updated = this.rentalDao.getById(id);
		
		updated.setRentDate(request.getRentDate());
		updated.setReturnDate(request.getReturnDate());;
		updated.setCar(request.getCar());
		updated.setCustomer(request.getCustomer());
		updated.setAdditionalServices(request.getAdditionalServices());
		updated.setReturnCity(request.getReturnCity());
		updated.setRentCity(request.getRentCity());
		
		if (!isSameCity(updated))
		{
			updated.setPrice(750);
		}
		
		this.rentalDao.save(updated);
		return new SuccessResult(Messages.RENTAL_UPDATE);
	}

	@Override
	public Result delete(int id) {
		
		IdValidationUtils.checkIfIdValid(id, this.rentalDao);
		this.rentalDao.deleteById(id);
		return new SuccessResult(Messages.RENTAL_DELETE);
	}
	
	// No controller needed
	@Override
	public DataResult<List<GetByCarIdRentalDto>> getByCarId(int carId) {
		
		List<Rental> result = this.rentalDao.findByCarId(carId);
		
		List<GetByCarIdRentalDto> response = result.stream()
				.map(rental -> this.modelMapperService.forDto()
				.map(rental, GetByCarIdRentalDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetByCarIdRentalDto>>(response);
	}
	
	private void isCarInMaintenance(int carId) {
		
		List<GetByCarIdCarMaintenanceDto> maintenanceDto = this.carMaintenanceService
				.getByCarId(carId).getData();
		
		
		for (GetByCarIdCarMaintenanceDto maintenance : maintenanceDto)
		{
			if (Objects.isNull(maintenance.getReturnDate()))
			{
				throw new BusinessException(Messages.RENTAL_CAR_IN_MAINTENANCE);
			}
		}
	}
	
	private boolean isSameCity (Rental rent) {
		
		return rent.getRentCity().getId() == rent.getReturnCity().getId();
	}
	
}
