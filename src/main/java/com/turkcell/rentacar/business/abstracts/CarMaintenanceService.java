package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.carmaintenances.GetByCarIdCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.carmaintenances.GetByIdCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.carmaintenances.ListCarMaintenanceDto;
import com.turkcell.rentacar.business.requests.carmaintenances.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carmaintenances.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

public interface CarMaintenanceService {
	DataResult<List<ListCarMaintenanceDto>> getAll();
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);
	DataResult<GetByIdCarMaintenanceDto> getById(int id);
	Result  update(int id, UpdateCarMaintenanceRequest updateCarMaintenanceRequest);
	Result  delete(int id);
	DataResult<List<GetByCarIdCarMaintenanceDto>> getByCarId(int carId);
}
