package com.turkcell.rentacar.business.requests.carmaintenances;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {
	@NotNull
	@Size(min = 2, max = 255, message = "Invalid Size for description")
	private String description;
	
	@NotNull
	private int carId;
}
