package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.rentals.GetByIdRentalDto;
import com.turkcell.rentacar.business.dtos.rentals.ListRentalDto;
import com.turkcell.rentacar.business.requests.rentals.CreateRentalRequest;
import com.turkcell.rentacar.business.requests.rentals.UpdateRentalRequest;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.ErrorResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.CarMaintenanceDao;
import com.turkcell.rentacar.dataaccess.abstracts.RentalDao;
import com.turkcell.rentacar.entities.concretes.CarMaintenance;
import com.turkcell.rentacar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {
	
	/* 
	 * Todo: Apply the constraints.
	 */
	
	private RentalDao rentalDao;
	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarMaintenanceDao carMaintenanceDao) {
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carMaintenanceDao = carMaintenanceDao;
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
		var result = this.rentalDao.findById(id);
		GetByIdRentalDto response = this.modelMapperService.forDto().map(result, GetByIdRentalDto.class);
		
		return new SuccessDataResult<GetByIdRentalDto>(response); 
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Rental request = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		if (isCarInMaintenance(request))
		{
			return new ErrorResult("Rental.InMaintenance");
		}
		
		this.rentalDao.save(request);
		
		return new SuccessResult("Rental.Add");
	}

	@Override
	public Result update(int id, UpdateRentalRequest updateRentalRequest) {
		Rental request = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		Rental updated = this.rentalDao.getById(id);
		
		updated.setRentDate(request.getRentDate());
		updated.setReturnDate(request.getReturnDate());;
		updated.setCar(request.getCar());
		updated.setCustomer(request.getCustomer());
		updated.setAdditionalServices(request.getAdditionalServices());
		
		return new SuccessResult("Rental.Update");
	}

	@Override
	public Result delete(int id) {
		this.rentalDao.deleteById(id);
		return new SuccessResult("Rental.Delete");
	}
	
	private boolean isCarInMaintenance(Rental request) {
		
		for (CarMaintenance carMaintenance : request.getCar().getCarMaintenances())
		{
			if (carMaintenance.getReturnDate() == null)
			{
				return true;
			}
		}
		return false;
	}
	
}
