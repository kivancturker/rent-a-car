package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.dtos.GetByIdColorDto;
import com.turkcell.rentacar.business.dtos.ListColorDto;
import com.turkcell.rentacar.business.requests.CreateColorRequest;
import com.turkcell.rentacar.business.requests.UpdateColorRequest;

@Service
public interface ColorService {
	List<ListColorDto> getAll();
	void add(CreateColorRequest createColorRequest);
	GetByIdColorDto getById(int id);
	void update(UpdateColorRequest updateColorRequest, String colorName);
}
