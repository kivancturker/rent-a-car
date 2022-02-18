package com.turkcell.rentacar.business.dtos;

import com.turkcell.rentacar.entities.concretes.Brand;
import com.turkcell.rentacar.entities.concretes.Color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDto {
	private int carId;
	private String carName;
	private double dailyPrice;
	private String modelYear;
	private String description;
	private Brand brand;
	private Color color;
}
