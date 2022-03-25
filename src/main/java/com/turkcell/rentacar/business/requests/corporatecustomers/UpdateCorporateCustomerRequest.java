package com.turkcell.rentacar.business.requests.corporatecustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCorporateCustomerRequest {
	private String taxNumber;
	private String companyName;
	private String email;
}
