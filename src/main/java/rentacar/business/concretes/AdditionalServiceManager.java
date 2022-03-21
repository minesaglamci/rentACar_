package rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rentacar.business.abstracts.AdditionalServiceService;
import rentacar.business.dtos.additionalService.AdditionalServiceDto;
import rentacar.business.dtos.additionalService.ListAdditionalServiceDto;
import rentacar.business.requests.additionalService.CreateAdditionalServiceRequest;
import rentacar.business.requests.additionalService.UpdateAdditionalServiceRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.mapping.ModelMapperService;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;
import rentacar.core.utilities.results.SuccessDataResult;
import rentacar.core.utilities.results.SuccessResult;
import rentacar.dataAccess.abstracts.AdditionalServiceDao;
import rentacar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao,
			ModelMapperService modelMapperService) {
		
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}


	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		
		checkAdditionalServiceId(updateAdditionalServiceRequest.getAdditionalServiceId());
		checkAdditionalServiceName(updateAdditionalServiceRequest.getAdditionalServiceName());
		
		AdditionalService additionalService = this.modelMapperService.forRequest()
			.map(updateAdditionalServiceRequest, AdditionalService.class);
		
		this.additionalServiceDao.save(additionalService);
		
		return new SuccessDataResult<UpdateAdditionalServiceRequest>(updateAdditionalServiceRequest,
			"Additional Service with name " + additionalService.getAdditionalServiceName() + "is updated");
	}

	@Override
	public Result create(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		
		checkAdditionalServiceId(createAdditionalServiceRequest.getAdditionalServiceId());
		checkAdditionalServiceName(createAdditionalServiceRequest.getAdditionalServiceName());
		
		AdditionalService additionalService = this.modelMapperService.forRequest()
				.map(createAdditionalServiceRequest, AdditionalService.class);
		
			this.additionalServiceDao.save(additionalService);
			
			return new SuccessDataResult<CreateAdditionalServiceRequest>(createAdditionalServiceRequest,
				"Additional Service with name " + additionalService.getAdditionalServiceName() + "is updated");

	}

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getAll() {
		
		List<AdditionalService> additionalServices = this.additionalServiceDao.getAll();
		
		List<ListAdditionalServiceDto> response = additionalServices.stream()
				.map(additionalService -> this.modelMapperService.forDto().map(additionalService, ListAdditionalServiceDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListAdditionalServiceDto>>(response, "Succeeded!");
	}

	@Override
	public DataResult<AdditionalServiceDto> getById(int additionalServiceId) {
		
		checkAdditionalServiceId(additionalServiceId);
		
		AdditionalService additionalService = this.additionalServiceDao.getByAdditionalServiceId(additionalServiceId);
		
		AdditionalServiceDto additionalServiceDto = this.modelMapperService.forDto()
			.map(additionalService, AdditionalServiceDto.class);
		
		return new SuccessDataResult<AdditionalServiceDto>(additionalServiceDto, "Additional Service getted by id");
	}


	@Override
	public Result delete(int additionalServiceId) {
		
		checkAdditionalServiceId(additionalServiceId);
	
		String additionalServiceNameBefore = this.additionalServiceDao
			.getByAdditionalServiceId(additionalServiceId).getAdditionalServiceName();
		this.additionalServiceDao.deleteById(additionalServiceId);
		
		return new SuccessResult("Additional Service is deleted: " + additionalServiceNameBefore);
	}

	private void checkAdditionalServiceId(int additionalServiceId){
		
		if (!this.additionalServiceDao.existsById(additionalServiceId)) {
			
			throw new BusinessException("Additional Service with id" + additionalServiceId + "is not exist");
		}
	}
	

	private void checkAdditionalServiceName(String additionalServiceName){
		
		if (!this.additionalServiceDao.existsByAdditionalServiceName(additionalServiceName)) {
			
			throw new BusinessException("Additional Service with id" + additionalServiceName + "is not exist");
		}
	}


	@Override
	public List<AdditionalService> getByRentalId(int rentalId) {
		// TODO Auto-generated method stub
		return null;
	}
}
