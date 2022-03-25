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

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.brands.GetByIdBrandDto;
import com.turkcell.rentacar.business.dtos.brands.ListBrandDto;
import com.turkcell.rentacar.business.requests.brands.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.brands.UpdateBrandRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
	private BrandService brandService;

	@Autowired
	public BrandController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}
	
	@GetMapping
	public DataResult<List<ListBrandDto>> getAll() {
		return this.brandService.getAll();
	}
	
	@GetMapping(path = "{id}")
	public DataResult<GetByIdBrandDto> getById(@PathVariable(required=true, name="id") int id) {
		return this.brandService.getById(id);
	}
	
	@PostMapping
	public Result add(@RequestBody CreateBrandRequest createProductRequest) {
		return this.brandService.add(createProductRequest);
	}
	
	
	
	@PutMapping(path = "{id}")
	public Result  update(@PathVariable(required = true, name="id") int id, 
				@RequestBody UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(id, updateBrandRequest);
	}
	
	@DeleteMapping(path = "{id}")
	public Result  delete(@PathVariable(required = true, name="id") int id) {
		return this.brandService.delete(id);
	}
}
