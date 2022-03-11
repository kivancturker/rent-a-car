package com.turkcell.rentacar.business.dtos.customers;

import java.util.List;

import com.turkcell.rentacar.entities.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCustomerDto {
	private int id;
	private List<Rental> rentals;
}
