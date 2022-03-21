package rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import rentacar.business.dtos.carMaintenance.CarMaintenanceDto;
import rentacar.business.dtos.carMaintenance.ListCarMaintenanceDto;
import rentacar.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import rentacar.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;

public interface CarMaintenanceService {

	Result create(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException;
	
	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException;

	Result delete(int carMaintenanceId) throws BusinessException;
	
	DataResult<List<ListCarMaintenanceDto>> getAll() throws BusinessException;

	DataResult<CarMaintenanceDto> getById(int carMaintenanceId) throws BusinessException;

	boolean checkIfCarMaintenanceExists(int carId);

	DataResult<List<ListCarMaintenanceDto>> getCarMaintenanceByCarId(int carId);

}