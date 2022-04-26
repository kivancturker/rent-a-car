package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.colors.GetByIdColorDto;
import com.turkcell.rentacar.business.dtos.colors.ListColorDto;
import com.turkcell.rentacar.business.exceptions.BusinessException;
import com.turkcell.rentacar.business.requests.colors.CreateColorRequest;
import com.turkcell.rentacar.business.requests.colors.UpdateColorRequest;
import com.turkcell.rentacar.core.utils.constants.Messages;
import com.turkcell.rentacar.core.utils.helpers.IdValidationUtils;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.ColorDao;
import com.turkcell.rentacar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {
	private ColorDao colorDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		super();
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListColorDto>> getAll() {
		var result = this.colorDao.findAll();
		List<ListColorDto> response = result.stream()
				.map(color -> this.modelMapperService.forDto().map(color, ListColorDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListColorDto>>(response);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		
		checkIfColorNameExist(color.getColorName());
		
		this.colorDao.save(color);
		return new SuccessResult(Messages.COLOR_ADD);
	}

	@Override
	public DataResult<GetByIdColorDto> getById(int id) {
		IdValidationUtils.checkIfIdValid(id, this.colorDao);
		var result = this.colorDao.getById(id);
		GetByIdColorDto response = this.modelMapperService.forDto().map(result, GetByIdColorDto.class);
		return new SuccessDataResult<GetByIdColorDto>(response);
	}

	@Override
	public Result update(int id, UpdateColorRequest updateColorRequest) {
		IdValidationUtils.checkIfIdValid(id, this.colorDao);
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		Color colorToUpdate = this.colorDao.getById(id);
		colorToUpdate.setColorName(color.getColorName());
		
		this.colorDao.save(colorToUpdate);
		return new SuccessResult(Messages.COLOR_UPDATE);
	}

	@Override
	public Result delete(int id) {
		IdValidationUtils.checkIfIdValid(id, this.colorDao);
		this.colorDao.deleteById(id);
		return new SuccessResult(Messages.COLOR_DELETE);
	}
	
	private void checkIfColorNameExist(String colorName) {
		
		if(Objects.nonNull(this.colorDao.findByColorName(colorName)))
		{
			throw new BusinessException(Messages.COLOR_NAME_EXIST);
		}
	}

}
