package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.GetByIdCarDto;
import com.turkcell.rentacar.business.dtos.ListCarDto;
import com.turkcell.rentacar.business.requests.CreateCarRequest;
import com.turkcell.rentacar.business.requests.UpdateCarRequest;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.CarDao;
import com.turkcell.rentacar.entities.concretes.Car;

@Service
public class CarManager implements CarService {
	private CarDao carDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		super();
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public DataResult<List<ListCarDto>> getAll() {
		var result = this.carDao.findAll();
		List<ListCarDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult("Car Added");
	}

	@Override
	public DataResult<GetByIdCarDto> getById(int id) {
		var result = this.carDao.getById(id);
		GetByIdCarDto response = this.modelMapperService.forDto().map(result, GetByIdCarDto.class);
		return new SuccessDataResult<GetByIdCarDto>(response);
	}

	@Override
	public Result update(int id, UpdateCarRequest updateCarRequest) {
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		Car carToUpdate = this.carDao.getById(id);
		carToUpdate.setBrand(car.getBrand());
		carToUpdate.setColor(car.getColor());
		carToUpdate.setDailyPrice(car.getDailyPrice());
		carToUpdate.setDescription(car.getDescription());
		carToUpdate.setModelYear(car.getModelYear());
		
		this.carDao.save(carToUpdate);
		return new SuccessResult("Car Updated");
	}

	@Override
	public Result delete(int id) {
		this.carDao.deleteById(id);
		return new SuccessResult("Car Deleted");
	}

}
