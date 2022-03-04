package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.GetByIdCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.ListCarMaintenanceDto;
import com.turkcell.rentacar.business.requests.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

public interface CarMaintenanceService {
	DataResult<List<ListCarMaintenanceDto>> getAll();
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);
	DataResult<GetByIdCarMaintenanceDto> getById(int id);
	Result  update(int id, UpdateCarMaintenanceRequest updateCarMaintenanceRequest);
	Result  delete(int id);
}
