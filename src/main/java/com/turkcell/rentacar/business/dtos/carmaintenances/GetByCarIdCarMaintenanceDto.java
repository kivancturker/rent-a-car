package com.turkcell.rentacar.business.dtos.carmaintenances;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByCarIdCarMaintenanceDto {
	
	private int id;

	private String description;
	
	private LocalDate returnDate;
}
