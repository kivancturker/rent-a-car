package com.turkcell.rentacar.business.dtos.colors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListColorDto {
	private int colorId;
	private String colorName;
}
