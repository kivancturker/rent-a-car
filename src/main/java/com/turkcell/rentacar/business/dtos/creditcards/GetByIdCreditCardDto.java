package com.turkcell.rentacar.business.dtos.creditcards;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCreditCardDto {
	private int id;
	
	private String ownerName;
	
	private String cardNumber;
	
	private int cvvNumber;
	
	private List<Integer> paymentId;
}
