package com.turkcell.rentacar.business.dtos.additionalservices;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdAdditionalServiceDto {

	private int id;
	private String serviceName;
	private BigInteger dailyPrice;
}
