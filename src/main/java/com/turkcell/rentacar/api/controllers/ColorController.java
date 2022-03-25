package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.colors.GetByIdColorDto;
import com.turkcell.rentacar.business.dtos.colors.ListColorDto;
import com.turkcell.rentacar.business.requests.colors.CreateColorRequest;
import com.turkcell.rentacar.business.requests.colors.UpdateColorRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

@RestController
@RequestMapping("/api/colors")
public class ColorController {
	private ColorService colorService;

	public ColorController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}
	
	@GetMapping
	public DataResult<List<ListColorDto>> getAll() {
		return this.colorService.getAll();
	}
	
	@GetMapping(path = "{id}")
	public DataResult<GetByIdColorDto> getById(@PathVariable(required = true, name = "id") int id) {
		return this.colorService.getById(id);
	}
	
	@PostMapping
	public Result add(@RequestBody CreateColorRequest createColorRequest) {
		return this.colorService.add(createColorRequest);
	}
	
	@PutMapping(path = {"id"})
	public Result update(@PathVariable(required = true, name="id") int id, 
				@RequestBody UpdateColorRequest updateColorRequest) {
		return this.colorService.update(id, updateColorRequest);
	}
	
	@DeleteMapping(path = "{id}")
	public Result delete(@PathVariable(required = true, name="id") int id) {
		return this.colorService.delete(id);
	}
}
