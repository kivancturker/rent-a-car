package com.turkcell.rentacar.business.requests.creditcards;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCreditCardRequest {
	private String ownerName;
	
	private String cardNumber;
	
	private int cvvNumber;
	
	private List<Integer> paymentId;
}
