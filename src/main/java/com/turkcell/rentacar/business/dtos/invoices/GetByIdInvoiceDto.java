package com.turkcell.rentacar.business.dtos.invoices;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdInvoiceDto {
	private LocalDate billingDate;

	private LocalDate rentalDate;

	private int totalRentDay;

	private double totalPrice;

	private int customerId;

	private int rentalId;
}
