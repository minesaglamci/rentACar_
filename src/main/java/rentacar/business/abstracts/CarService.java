package rentacar.business.abstracts;

import java.util.List;

import rentacar.business.dtos.car.CarDto;
import rentacar.business.dtos.car.GetCarByDailyPriceDto;
import rentacar.business.dtos.car.ListCarDto;
import rentacar.business.requests.car.CreateCarRequest;
import rentacar.business.requests.car.DeleteCarRequest;
import rentacar.business.requests.car.UpdateCarRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;

public interface CarService {
	
	Result create(CreateCarRequest createCarRequest) throws BusinessException;
	
	Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException;
	
	Result update(UpdateCarRequest updateCarRequest) throws BusinessException;
	
	DataResult<List<ListCarDto>> getAll() throws BusinessException;
	
	DataResult<CarDto> getById(int carId) throws BusinessException;

	DataResult<List<GetCarByDailyPriceDto>> getCarByDailyPriceLessThanEqual(double dailyPrice) throws BusinessException;

	DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException;
	
	 DataResult<List<ListCarDto>> getAllSorted() throws BusinessException;

	boolean isCarExistsById(int carId);
	
}