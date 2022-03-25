package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.dtos.brands.GetByIdBrandDto;
import com.turkcell.rentacar.business.dtos.brands.ListBrandDto;
import com.turkcell.rentacar.business.requests.brands.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.brands.UpdateBrandRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;


@Service
public interface BrandService {
	DataResult<List<ListBrandDto>> getAll();
	Result  add(CreateBrandRequest createBrandRequest);
	DataResult<GetByIdBrandDto> getById(int id);
	Result  update(int id, UpdateBrandRequest updateBrandRequest);
	Result  delete(int id);
} 