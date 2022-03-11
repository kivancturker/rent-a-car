package com.turkcell.rentacar.business.dtos.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCustomerDto {
	private ListCustomerDto listCustomerDto;
}
