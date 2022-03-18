package com.turkcell.rentacar.business.requests.invoices;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateInvoiceRequest {
	LocalDate startDate;
	LocalDate endDate;
}
