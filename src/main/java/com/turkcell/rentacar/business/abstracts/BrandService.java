package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.dtos.GetByIdBrandDto;
import com.turkcell.rentacar.business.dtos.ListBrandDto;
import com.turkcell.rentacar.business.requests.CreateBrandRequest;


@Service
public interface BrandService {
	List<ListBrandDto> getAll();
	void add(CreateBrandRequest createBrandRequest);
	GetByIdBrandDto getById(int id);
} 