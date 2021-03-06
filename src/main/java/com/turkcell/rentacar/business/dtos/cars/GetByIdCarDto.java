package com.turkcell.rentacar.business.dtos.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCarDto {
	private int carId;
	private double dailyPrice;
	private String modelYear;
	private String description;
	private String brandName;
	private String colorName;
}
