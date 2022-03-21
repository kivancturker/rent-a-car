package com.turkcell.rentacar.business.dtos.payments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPaymentDto {
	private int id;
	private int invoiceNo;
	private String carOwnerName;
}
