package com.turkcell.rentacar.business.requests.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	private double dailyPrice;
	private String modelYear;
	private String description;
	private int brandId;
	private int colorId;
}
