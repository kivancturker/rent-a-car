package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.dtos.cars.GetByIdCarDto;
import com.turkcell.rentacar.business.dtos.cars.ListCarDto;
import com.turkcell.rentacar.business.requests.cars.CreateCarRequest;
import com.turkcell.rentacar.business.requests.cars.UpdateCarRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

@Service
public interface CarService {
	DataResult<List<ListCarDto>> getAll();
	Result add(CreateCarRequest createCarRequest);
	DataResult<GetByIdCarDto> getById(int id);
	Result update(int id, UpdateCarRequest updateColorRequest);
	Result delete(int id);
	public DataResult<List<ListCarDto>> getByDailyPriceLessThanEqual(double dailyPrice);
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize);
	public DataResult<List<ListCarDto>> getAllSorted(Direction direction);
}