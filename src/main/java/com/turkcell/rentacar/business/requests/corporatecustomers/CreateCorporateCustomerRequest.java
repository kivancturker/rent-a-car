package com.turkcell.rentacar.business.requests.corporatecustomers;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCorporateCustomerRequest {
	private String taxNumber;
	
	private String companyName;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String password;
}
