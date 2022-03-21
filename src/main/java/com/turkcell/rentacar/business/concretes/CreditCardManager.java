package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CreditCardService;
import com.turkcell.rentacar.business.dtos.creditcard.GetByIdCreditCardDto;
import com.turkcell.rentacar.business.dtos.creditcard.ListCreditCardDto;
import com.turkcell.rentacar.business.requests.creditcard.CreateCreditCardRequest;
import com.turkcell.rentacar.business.requests.creditcard.UpdateCreditCardRequest;
import com.turkcell.rentacar.core.utils.constants.Messages;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.CreditCardDao;
import com.turkcell.rentacar.entities.concretes.CreditCard;

@Service
public class CreditCardManager implements CreditCardService {
	private CreditCardDao creditCardDao;
	private ModelMapperService modelMapperService;
	
	public CreditCardManager(CreditCardDao creditCardDao, ModelMapperService modelMapperService) {
		this.creditCardDao = creditCardDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListCreditCardDto>> getAll() {
		var result = this.creditCardDao.findAll();
		List<ListCreditCardDto> response = result.stream()
		.map(creditCard -> this.modelMapperService.forDto()
		.map(creditCard, ListCreditCardDto.class))
		.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCreditCardDto>>(response);
	}

	@Override
	public Result add(CreateCreditCardRequest createCreditCardRequest) {
		CreditCard creditCard = this.modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
		this.creditCardDao.save(creditCard);
		return new SuccessResult(Messages.CREDIT_CARD_ADD);
	}

	@Override
	public DataResult<GetByIdCreditCardDto> getById(int id) {
		var result = this.creditCardDao.getById(id);
		GetByIdCreditCardDto response = this.modelMapperService.forDto().map(result, GetByIdCreditCardDto.class);
		
		return new SuccessDataResult<GetByIdCreditCardDto>(response);
	}

	@Override
	public Result update(int id, UpdateCreditCardRequest updateCreditCardRequest) {
		CreditCard creditCard = this.modelMapperService.forRequest().map(updateCreditCardRequest, CreditCard.class);
		CreditCard updated = this.creditCardDao.getById(id);
		
		updated.setCardNumber(creditCard.getCardNumber());
		updated.setCvvNumber(creditCard.getCvvNumber());
		updated.setOwnerName(creditCard.getOwnerName());
		updated.setPayments(creditCard.getPayments());
		
		this.creditCardDao.save(updated);
		return new SuccessResult(Messages.CREDIT_CARD_UPDATE);
	}

	@Override
	public Result delete(int id) {
		this.creditCardDao.deleteById(id);
		return new SuccessResult(Messages.CREDIT_CARD_DELETE);
	}

}
