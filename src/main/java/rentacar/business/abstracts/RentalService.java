package rentacar.business.abstracts;

import java.util.List;

import rentacar.business.dtos.rental.ListRentalDto;
import rentacar.business.dtos.rental.RentalDto;
import rentacar.business.requests.rental.CreateRentalRequest;
import rentacar.business.requests.rental.UpdateRentalRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;

public interface RentalService {
	
	Result create(CreateRentalRequest createRentalRequest) throws BusinessException;
	
	Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException;
	
	DataResult<List<ListRentalDto>> getAll();
	
	DataResult<RentalDto> getById(int carId) throws BusinessException;

	Result delete(int rentalId);

	DataResult<List<ListRentalDto>> getAllByCar(int carId) throws BusinessException;
}