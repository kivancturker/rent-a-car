package com.turkcell.rentacar.business.dtos.brands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdBrandDto {
	private int brandId;
	private String brandName;
}
