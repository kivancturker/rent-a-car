package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.rentals.GetByIdRentalDto;
import com.turkcell.rentacar.business.dtos.rentals.ListRentalDto;
import com.turkcell.rentacar.business.requests.rentals.CreateRentalRequest;
import com.turkcell.rentacar.business.requests.rentals.UpdateRentalRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	private RentalService rentalService;

	@Autowired
	public RentalController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}
	
	@GetMapping
	public DataResult<List<ListRentalDto>> getAll() {
		return this.rentalService.getAll();
	}
	
	@GetMapping(path = "{id}")
	public DataResult<GetByIdRentalDto> getById(@PathVariable(required=true, name="id") int id) {
		return this.rentalService.getById(id);
	}
	
	@PostMapping
	public Result add(@RequestBody CreateRentalRequest createRentalRequest) {
		return this.rentalService.add(createRentalRequest);
	}
	
	
	
	@PutMapping(path = "{id}")
	public Result  update(@PathVariable(required = true, name="id") int id, 
				@RequestBody UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.update(id, updateRentalRequest);
	}
	
	@DeleteMapping(path = "{id}")
	public Result  delete(@PathVariable(required = true, name="id") int id) {
		return this.rentalService.delete(id);
	}
}
