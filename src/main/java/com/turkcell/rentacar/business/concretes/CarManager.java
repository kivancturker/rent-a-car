package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.cars.GetByIdCarDto;
import com.turkcell.rentacar.business.dtos.cars.ListCarDto;
import com.turkcell.rentacar.business.exceptions.BusinessException;
import com.turkcell.rentacar.business.requests.cars.CreateCarRequest;
import com.turkcell.rentacar.business.requests.cars.UpdateCarRequest;
import com.turkcell.rentacar.core.utils.constants.Messages;
import com.turkcell.rentacar.core.utils.helpers.IdValidationUtils;
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
	private ColorService colorService;
	@Autowired
	private BrandService brandService;
	
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
		
		if (!isColorExist(car.getColor().getColorId()) || !isBrandExist(car.getBrand().getBrandId()))
		{
			throw new BusinessException(Messages.CAR_ADD_ERROR);
		}
		
		this.carDao.save(car);
		
		return new SuccessResult(Messages.CAR_ADD);
	}

	@Override
	public DataResult<GetByIdCarDto> getById(int id) {
		
		IdValidationUtils.checkIfIdValid(id, this.carDao);
		var result = this.carDao.getById(id);
		GetByIdCarDto response = this.modelMapperService.forDto().map(result, GetByIdCarDto.class);
		
		return new SuccessDataResult<GetByIdCarDto>(response);
	}

	@Override
	public Result update(int id, UpdateCarRequest updateCarRequest) {
		
		IdValidationUtils.checkIfIdValid(id, this.carDao);
		
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		Car carToUpdate = this.carDao.getById(id);
		
		if (Objects.nonNull(car.getBrand()))
		{
			carToUpdate.setBrand(car.getBrand());
		}
		
		if (Objects.nonNull(car.getColor()))
		{
			carToUpdate.setColor(car.getColor());
		}
		
		if (Objects.nonNull(car.getDailyPrice()))
		{
			carToUpdate.setDailyPrice(car.getDailyPrice());
		}
		
		if (Objects.nonNull(car.getDescription()))
		{
			carToUpdate.setDescription(car.getDescription());
		}
		
		if (Objects.nonNull(car.getModelYear()))
		{
			carToUpdate.setModelYear(car.getModelYear());
		}
		
		
		this.carDao.save(carToUpdate);
		
		return new SuccessResult(Messages.CAR_UPDATE);
	}

	@Override
	public Result delete(int id) {
		
		IdValidationUtils.checkIfIdValid(id, this.carDao);
		
		this.carDao.deleteById(id);
		
		return new SuccessResult(Messages.CAR_DELETE);
	}
	
	@Override
	public DataResult<List<ListCarDto>> getByDailyPriceLessThanEqual(double dailyPrice) {

		List<Car> result = this.carDao.getByDailyPriceLessThanEqual(dailyPrice);
		List<ListCarDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarDto>>(response,Messages.CAR_LIST_LEQ);
	}
	
	@Override
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		List<Car> result = this.carDao.findAll(pageable).getContent();
		List<ListCarDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarDto>>(response, Messages.CAR_PAGEABLE);
	}
	
	@Override
	public DataResult<List<ListCarDto>> getAllSorted(Direction direction) {

		Sort sort = Sort.by(direction, "dailyPrice");

		List<Car> result = this.carDao.findAll(sort);
		List<ListCarDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarDto>>(response, Messages.CAR_SORTED);
	}
	
	
	
	private boolean isColorExist(int colorId) {
		
		return this.colorService.getById(colorId).isSuccess();
	}
	
	private boolean isBrandExist(int brandId) {
		
		return this.brandService.getById(brandId).isSuccess();
	}

}
