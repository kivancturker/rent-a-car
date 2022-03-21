package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.requests.payments.CreatePaymentRequest;
import com.turkcell.rentacar.core.utils.results.Result;

public interface PosService {
	public Result payment(CreatePaymentRequest createPaymentRequest);
}
