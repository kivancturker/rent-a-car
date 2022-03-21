package com.turkcell.rentacar.business.requests.payments;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {
	@NotNull
	private int invoiceNo;
	
	private int creditCardId;
}
