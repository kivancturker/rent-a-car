package com.turkcell.rentacar.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	private String carName;
	private double dailyPrice;
	private String modelYear;
	private String description;
	private int brandId;
	private int colorId;
}
