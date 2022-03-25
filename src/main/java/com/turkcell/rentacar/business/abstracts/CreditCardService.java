package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.creditcards.GetByIdCreditCardDto;
import com.turkcell.rentacar.business.dtos.creditcards.ListCreditCardDto;
import com.turkcell.rentacar.business.requests.creditcards.CreateCreditCardRequest;
import com.turkcell.rentacar.business.requests.creditcards.UpdateCreditCardRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

public interface CreditCardService {
	DataResult<List<ListCreditCardDto>> getAll();
	Result  add(CreateCreditCardRequest createCreditCardRequest);
	DataResult<GetByIdCreditCardDto> getById(int id);
	Result  update(int id, UpdateCreditCardRequest updateCreditCardRequest);
	Result  delete(int id);
}
