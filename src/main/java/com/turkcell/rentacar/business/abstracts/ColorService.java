package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.dtos.GetByIdColorDto;
import com.turkcell.rentacar.business.dtos.ListColorDto;
import com.turkcell.rentacar.business.requests.CreateColorRequest;
import com.turkcell.rentacar.business.requests.UpdateColorRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

@Service
public interface ColorService {
	DataResult<List<ListColorDto>> getAll();
	Result add(CreateColorRequest createColorRequest);
	DataResult<GetByIdColorDto> getById(int id);
	Result update(int id, UpdateColorRequest updateColorRequest);
	Result delete(int id);
}
