package com.turkcell.rentacar.business.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CreditCardService;
import com.turkcell.rentacar.business.abstracts.PosService;
import com.turkcell.rentacar.business.dtos.creditcard.GetByIdCreditCardDto;
import com.turkcell.rentacar.business.outservices.FakeHalkBankManager;
import com.turkcell.rentacar.business.requests.payments.CreatePaymentRequest;
import com.turkcell.rentacar.core.utils.results.Result;

@Service
@Primary
public class FakeHalkBankServiceAdapter implements PosService {
	private final CreditCardService creditCardService;

	@Autowired
	public FakeHalkBankServiceAdapter(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}

	@Override
	public Result payment(CreatePaymentRequest createPaymentRequest) {
		FakeHalkBankManager fakeHalkBankManager = new FakeHalkBankManager();
		GetByIdCreditCardDto creditCardDto = creditCardService.getById(createPaymentRequest.getCreditCardId())
				.getData();
		return fakeHalkBankManager.odemeYap(creditCardDto.getCvvNumber(), creditCardDto.getOwnerName(),
				creditCardDto.getCardNumber());

	}

}