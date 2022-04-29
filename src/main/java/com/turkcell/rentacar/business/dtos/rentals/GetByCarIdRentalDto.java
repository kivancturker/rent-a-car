package com.turkcell.rentacar.business.dtos.rentals;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByCarIdRentalDto {

	private int id;
	private int customerId;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private List<String> additionalServiceServiceNames;
}
