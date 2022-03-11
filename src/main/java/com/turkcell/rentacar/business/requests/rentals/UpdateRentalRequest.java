package com.turkcell.rentacar.business.requests.rentals;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalRequest {
	@NotNull
	private LocalDate rentDate;
	@NotNull
	private int customerId;
	@NotNull
	private int carId;
	private List<Integer> additionalServiceId;
	private int rentCityId;
	private LocalDate returnDate;
	private int returnCityId;
}
