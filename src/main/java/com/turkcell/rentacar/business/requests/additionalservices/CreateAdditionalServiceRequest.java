package com.turkcell.rentacar.business.requests.additionalservices;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceRequest {
	
	private String serviceName;
	private BigInteger dailyPrice;
}
