package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.GetByIdBrandDto;
import com.turkcell.rentacar.business.dtos.ListBrandDto;
import com.turkcell.rentacar.business.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.UpdateBrandRequest;
import com.turkcell.rentacar.core.utils.constants.Messages;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.BrandDao;
import com.turkcell.rentacar.entities.concretes.Brand;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class BrandManager implements BrandService {
	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		super();
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListBrandDto>> getAll() {
		var result = this.brandDao.findAll();
		List<ListBrandDto> response = result.stream()
		.map(brand -> this.modelMapperService.forDto()
		.map(brand, ListBrandDto.class))
		.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListBrandDto>>(response);
	}

	@Override
	public Result add(@RequestBody CreateBrandRequest createBrandRequest) {
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult();
	}

	@Override
	public DataResult<GetByIdBrandDto> getById(int id) {
		var result = this.brandDao.getById(id);
		GetByIdBrandDto response = this.modelMapperService.forDto().map(result, GetByIdBrandDto.class);
		
		return new SuccessDataResult<GetByIdBrandDto>(response);
	}

	@Override
	public Result update(int id, UpdateBrandRequest updateBrandRequest) {
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		Brand brandToUpdate = this.brandDao.getById(id);
		brandToUpdate.setBrandName(brand.getBrandName());
		
		this.brandDao.save(brandToUpdate);
		return new SuccessResult(Messages.BRAND_UPDATE);
	}

	@Override
	public Result delete(int id) {
		this.brandDao.deleteById(id);
		return new SuccessResult(Messages.BRAND_DELETE);
	}
	
}
