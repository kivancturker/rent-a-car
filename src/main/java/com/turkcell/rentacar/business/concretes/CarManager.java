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
import com.turkcell.rentacar.dataaccess.abstracts.CarDao;
import com.turkcell.rentacar.entities.concretes.Brand;
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
	public List<ListCarDto> getAll() {
		var result = this.carDao.findAll();
		List<ListCarDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public void add(CreateCarRequest createCarRequest) {
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		
	}

	@Override
	public GetByIdCarDto getById(int id) {
		var result = this.carDao.getById(id);
		GetByIdCarDto response = this.modelMapperService.forDto().map(result, GetByIdCarDto.class);
		return response;
	}

	@Override
	public void update(UpdateCarRequest updateCarRequest, String carName) {
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		Car carToUpdate = this.carDao.findByCarName(carName);
		
		
		this.carDao.save(carToUpdate);
		
	}

}
