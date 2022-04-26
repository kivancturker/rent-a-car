package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.brands.GetByIdBrandDto;
import com.turkcell.rentacar.business.dtos.brands.ListBrandDto;
import com.turkcell.rentacar.business.exceptions.BusinessException;
import com.turkcell.rentacar.business.requests.brands.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.brands.UpdateBrandRequest;
import com.turkcell.rentacar.core.utils.constants.Messages;
import com.turkcell.rentacar.core.utils.helpers.IdValidationUtils;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.BrandDao;
import com.turkcell.rentacar.entities.concretes.Brand;

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
	public Result add(CreateBrandRequest createBrandRequest) {
		
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		
		checkIfBrandNameExist(brand.getBrandName());
		
		this.brandDao.save(brand);
		return new SuccessResult(Messages.BRAND_ADD);
	}

	@Override
	public DataResult<GetByIdBrandDto> getById(int id) {
		
		IdValidationUtils.checkIfIdValid(id, this.brandDao);
		var result = this.brandDao.getById(id);
		GetByIdBrandDto response = this.modelMapperService.forDto().map(result, GetByIdBrandDto.class);
		
		return new SuccessDataResult<GetByIdBrandDto>(response);
	}

	@Override
	public Result update(int id, UpdateBrandRequest updateBrandRequest) {
		
		IdValidationUtils.checkIfIdValid(id, this.brandDao);
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		Brand brandToUpdate = this.brandDao.getById(id);
		brandToUpdate.setBrandName(brand.getBrandName());
		
		this.brandDao.save(brandToUpdate);
		return new SuccessResult(Messages.BRAND_UPDATE);
	}

	@Override
	public Result delete(int id) {
		
		IdValidationUtils.checkIfIdValid(id, this.brandDao);
		this.brandDao.deleteById(id);
		return new SuccessResult(Messages.BRAND_DELETE);
	}
	
	private void checkIfBrandNameExist(String brandName) {
		
		if(Objects.nonNull(this.brandDao.findByBrandName(brandName)))
		{
			throw new BusinessException(Messages.BRAND_NAME_EXIST);
		}
	}
	
}
