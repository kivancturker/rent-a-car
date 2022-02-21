package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.GetByIdColorDto;
import com.turkcell.rentacar.business.dtos.ListColorDto;
import com.turkcell.rentacar.business.requests.CreateColorRequest;
import com.turkcell.rentacar.business.requests.UpdateColorRequest;

@RestController
@RequestMapping("/api/colors")
public class ColorController {
	private ColorService colorService;

	public ColorController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}
	
	@GetMapping("/getall")
	public List<ListColorDto> getAll() {
		return this.colorService.getAll();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateColorRequest createColorRequest) {
		this.colorService.add(createColorRequest);
	}
	
	@GetMapping(path = {"/getById", "/getById/{id}"})
	public GetByIdColorDto getById(@PathVariable(required = true, name = "id") int id) {
		return this.colorService.getById(id);
	}
	
	@PutMapping("/update/{colorName}")
	public void update(@PathVariable(required = true, name="colorName") String colorName, 
				@RequestBody UpdateColorRequest updateColorRequest) {
		this.colorService.update(updateColorRequest, colorName);
	}
}
