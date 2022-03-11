package com.turkcell.rentacar.business.requests.rentals;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.turkcell.rentacar.entities.concretes.AdditionalService;
import com.turkcell.rentacar.entities.concretes.City;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
	@NotNull
	private LocalDate rentDate;
	@NotNull
	private int customerId;
	@NotNull
	private int carId;
	private List<Integer> additionalServiceId;
	private int rentCityId;
}
