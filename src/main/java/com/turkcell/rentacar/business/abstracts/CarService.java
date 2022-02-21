package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.dtos.GetByIdCarDto;
import com.turkcell.rentacar.business.dtos.ListCarDto;
import com.turkcell.rentacar.business.requests.CreateCarRequest;
import com.turkcell.rentacar.business.requests.UpdateCarRequest;

@Service
public interface CarService {
	List<ListCarDto> getAll();
	void add(CreateCarRequest createCarRequest);
	GetByIdCarDto getById(int id);
	void update(UpdateCarRequest updateColorRequest, String carName);
}