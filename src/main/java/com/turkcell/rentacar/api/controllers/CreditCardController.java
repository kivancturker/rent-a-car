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

import com.turkcell.rentacar.business.abstracts.CreditCardService;
import com.turkcell.rentacar.business.dtos.creditcards.GetByIdCreditCardDto;
import com.turkcell.rentacar.business.dtos.creditcards.ListCreditCardDto;
import com.turkcell.rentacar.business.requests.creditcards.CreateCreditCardRequest;
import com.turkcell.rentacar.business.requests.creditcards.UpdateCreditCardRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {
	private CreditCardService creditCardService;

	@Autowired
	public CreditCardController(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}
	
	@GetMapping
	public DataResult<List<ListCreditCardDto>> getAll() {
		return this.creditCardService.getAll();
	}
	
	@GetMapping(path = "{id}")
	public DataResult<GetByIdCreditCardDto> getById(@PathVariable(required=true, name="id") int id) {
		return this.creditCardService.getById(id);
	}
	
	@PostMapping
	public Result add(@RequestBody CreateCreditCardRequest createCreditCardRequest) {
		return this.creditCardService.add(createCreditCardRequest);
	}
	
	
	
	@PutMapping(path = "{id}")
	public Result  update(@PathVariable(required = true, name="id") int id, 
				@RequestBody UpdateCreditCardRequest updateCreditCardRequest) {
		return this.creditCardService.update(id, updateCreditCardRequest);
	}
	
	@DeleteMapping(path = "{id}")
	public Result  delete(@PathVariable(required = true, name="id") int id) {
		return this.creditCardService.delete(id);
	}
}
