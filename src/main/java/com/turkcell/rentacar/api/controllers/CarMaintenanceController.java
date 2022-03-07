package com.turkcell.rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.dtos.GetByIdCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.ListCarMaintenanceDto;
import com.turkcell.rentacar.business.requests.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

@RestController
@RequestMapping("/api/carMaintenances")
public class CarMaintenanceController {
	private CarMaintenanceService carMaintenanceService;

	@Autowired
	public CarMaintenanceController(CarMaintenanceService carMaintenanceService) {
		super();
		this.carMaintenanceService = carMaintenanceService;
	}
	
	@GetMapping
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		return this.carMaintenanceService.getAll();
	}
	
	@GetMapping(path = "{id}")
	public DataResult<GetByIdCarMaintenanceDto> getById(@PathVariable(required=true, name="id") int id) {
		return this.carMaintenanceService.getById(id);
	}
	
	@PostMapping
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		return this.carMaintenanceService.add(createCarMaintenanceRequest);
	}
	
	
	@PutMapping(path = "{id}")
	public Result update(@PathVariable(required = true, name="id") int id, 
				@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		return this.carMaintenanceService.update(id, updateCarMaintenanceRequest);
	}
	
	@DeleteMapping(path = "{id}")
	public Result  delete(@PathVariable(required = true, name="id") int id) {
		return this.carMaintenanceService.delete(id);
	}
}
