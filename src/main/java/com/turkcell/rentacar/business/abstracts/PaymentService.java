package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.payments.GetByIdPaymentDto;
import com.turkcell.rentacar.business.dtos.payments.ListPaymentDto;
import com.turkcell.rentacar.business.requests.payments.CreatePaymentRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

public interface PaymentService {
	public Result add(CreatePaymentRequest createPaymentRequest);
	DataResult<List<ListPaymentDto>> getAll();
	DataResult<GetByIdPaymentDto> getById(int paymentId);
}