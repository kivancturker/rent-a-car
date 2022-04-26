package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.cars.GetByIdCarDto;
import com.turkcell.rentacar.business.dtos.cars.ListCarDto;
import com.turkcell.rentacar.business.requests.cars.CreateCarRequest;
import com.turkcell.rentacar.business.requests.cars.UpdateCarRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

@RestController
@RequestMapping("/api/cars")
public class CarController {
	private CarService carService;

	public CarController(CarService carService) {
		super();
		this.carService = carService;
	}
	
	@GetMapping
	public DataResult<List<ListCarDto>> getAll() {
		return this.carService.getAll();
	}
	
	@GetMapping(path = "{id}")
	public DataResult<GetByIdCarDto> getById(@PathVariable(required = true, name = "id") int id) {
		return this.carService.getById(id);
	}
	
	@PostMapping
	public Result add(@RequestBody CreateCarRequest createCarRequest) {
		return this.carService.add(createCarRequest);
	}
	
	@PutMapping(path="{id}")
	public Result add(@PathVariable(required = true, name = "id") int id, 
				@RequestBody UpdateCarRequest updateCarRequest) {
		return this.carService.update(id, updateCarRequest);
	}
	
	@DeleteMapping(path = "{id}")
	public Result delete(@PathVariable(required = true, name="id") int id) {
		return this.carService.delete(id);
	}
	
	@GetMapping("/getAllPaged")
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
		return this.carService.getAllPaged(pageNo, pageSize);
	}

	
	@GetMapping("/getAllSorted")
	public DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction) {
		return this.carService.getAllSorted(direction);
	}

	@GetMapping("/getByDailyPriceLessThanEqual")
	public DataResult<List<ListCarDto>> getByDailyPriceLessThanEqual(double dailyPrice) {
		return this.carService.getByDailyPriceLessThanEqual(dailyPrice);
	}
	
}
