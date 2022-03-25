package com.turkcell.rentacar.business.dtos.corporatecustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdCorporateCustomerDto {
	private int id;
	private String taxNumber;
	private String companyName;
	private String email;
}
