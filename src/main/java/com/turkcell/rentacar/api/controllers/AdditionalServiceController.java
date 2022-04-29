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

import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.dtos.additionalservices.GetByIdAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.additionalservices.ListAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.additionalservices.CreateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.additionalservices.UpdateAdditionalServiceRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

@RestController
@RequestMapping("/api/additionalServices")
public class AdditionalServiceController {
	private AdditionalServiceService additionalServiceService;

	@Autowired
	public AdditionalServiceController(AdditionalServiceService additionalServiceService) {
		super();
		this.additionalServiceService = additionalServiceService;
	}
	
	@GetMapping
	public DataResult<List<ListAdditionalServiceDto>> getAll() {
		
		return this.additionalServiceService.getAll();
	}
	
	@GetMapping(path = "{id}")
	public DataResult<GetByIdAdditionalServiceDto> getById(
			@PathVariable(required=true, name="id") int id) {
		
		return this.additionalServiceService.getById(id);
	}
	
	@PostMapping
	public Result add(@RequestBody CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		
		return this.additionalServiceService
				.add(createAdditionalServiceRequest);
	}
	
	@PutMapping(path = "{id}")
	public Result  update(@PathVariable(required = true, name="id") int id, 
				@RequestBody UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		
		return this.additionalServiceService
				.update(id, updateAdditionalServiceRequest);
	}
	
	@DeleteMapping(path = "{id}")
	public Result  delete(
			@PathVariable(required = true, name="id") int id) {
		
		return this.additionalServiceService.delete(id);
	}
}
