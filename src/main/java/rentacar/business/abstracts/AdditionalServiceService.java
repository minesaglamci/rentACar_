package rentacar.business.abstracts;

import java.util.List;

import rentacar.business.dtos.additionalService.AdditionalServiceDto;
import rentacar.business.dtos.additionalService.ListAdditionalServiceDto;
import rentacar.business.requests.additionalService.CreateAdditionalServiceRequest;
import rentacar.business.requests.additionalService.UpdateAdditionalServiceRequest;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;
import rentacar.entities.concretes.AdditionalService;

public interface AdditionalServiceService {

	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);

	Result create(CreateAdditionalServiceRequest createAdditionalServiceRequest);

	DataResult<List<ListAdditionalServiceDto>> getAll();

	DataResult<AdditionalServiceDto> getById(int additionalServiceId);
	
	DataResult<AdditionalServiceDto> getServicePrice(double dailyPrice);
	
	Result delete(int additionalServiceId);
	
	List<AdditionalService> getByRentalId(int rentalId);
}