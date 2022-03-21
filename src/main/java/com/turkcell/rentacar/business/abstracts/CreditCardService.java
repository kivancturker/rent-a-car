package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.creditcard.GetByIdCreditCardDto;
import com.turkcell.rentacar.business.dtos.creditcard.ListCreditCardDto;
import com.turkcell.rentacar.business.requests.creditcard.CreateCreditCardRequest;
import com.turkcell.rentacar.business.requests.creditcard.UpdateCreditCardRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

public interface CreditCardService {
	DataResult<List<ListCreditCardDto>> getAll();
	Result  add(CreateCreditCardRequest createCreditCardRequest);
	DataResult<GetByIdCreditCardDto> getById(int id);
	Result  update(int id, UpdateCreditCardRequest updateCreditCardRequest);
	Result  delete(int id);
}
