package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.GetByIdBrandDto;
import com.turkcell.rentacar.business.dtos.ListBrandDto;
import com.turkcell.rentacar.business.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.UpdateBrandRequest;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
	private BrandService brandService;

	@Autowired
	public BrandController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}
	
	@GetMapping("/getall")
	public List<ListBrandDto> getAll() {
		return this.brandService.getAll();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateBrandRequest createProductRequest) {
		this.brandService.add(createProductRequest);
	}
	
	@GetMapping(path = {"/getbyid", "/getbyid/{id}"})
	public GetByIdBrandDto getById(@PathVariable(required = true, name = "id") int id) {
		return this.brandService.getById(id);
	}
	
	@PutMapping("/update/{brandName}")
	public void update(@PathVariable(required = true, name="brandName") String brandName, 
				@RequestBody UpdateBrandRequest updateBrandRequest) {
		this.brandService.update(updateBrandRequest, brandName);
	}
}
