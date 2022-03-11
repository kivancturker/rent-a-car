package com.turkcell.rentacar.business.requests.rentals;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalRequest {
	CreateRentalRequest createRentalRequest;
	private LocalDate returnDate;
}
