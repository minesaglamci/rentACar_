package rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.var;
import rentacar.business.abstracts.BrandService;
import rentacar.business.dtos.brand.ListBrandDto;
import rentacar.business.requests.brand.CreateBrandRequest;
import rentacar.business.requests.brand.DeleteBrandRequest;
import rentacar.business.requests.brand.UpdateBrandRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.mapping.ModelMapperService;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;
import rentacar.core.utilities.results.SuccessDataResult;
import rentacar.core.utilities.results.SuccessResult;
import rentacar.dataAccess.abstracts.BrandDao;
import rentacar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService{

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListBrandDto>> getAll() {
		var result = this.brandDao.findAll();
		List<ListBrandDto> response = result.stream()
				.map(brand -> this.modelMapperService.forDto().map(brand, ListBrandDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListBrandDto>>(response, "Succeeded!");
	}

	@Override
	public DataResult<ListBrandDto> getById(int brandId) throws BusinessException {
		Brand result = this.brandDao.getById(brandId);
		if (result != null) {
			ListBrandDto response = this.modelMapperService.forDto().map(result, ListBrandDto.class);
			return new SuccessDataResult<ListBrandDto>(response, "Succeeded!");
		}
		throw new BusinessException("Brand was not found!");
	}
	
	@Override
	public Result add(CreateBrandRequest createBrandRequest) throws BusinessException {
		
		checkBrandNames(createBrandRequest.getBrandName());
		
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);
		
		return new SuccessResult("Brand is added:" + createBrandRequest.getBrandName());
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException {
		
		checkIfBrandId(deleteBrandRequest.getBrandId());
		
		this.brandDao.deleteById(deleteBrandRequest.getBrandId());
		
		return new SuccessResult("Brand is deleted ");
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException {
		
		checkIfBrandId(updateBrandRequest.getBrandId());
		checkBrandNames(updateBrandRequest.getBrandName());
		
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);
		
		return new SuccessDataResult<UpdateBrandRequest>(updateBrandRequest,
				"Brand is updated : " + brand.getBrandName());
	}
	
	
	private void checkBrandNames(String brandName) throws BusinessException {
		if (!brandDao.getByBrandName(brandName)) {
			throw new BusinessException("Brand with name" + brandName + "is not exist");
		} 
	}
	
	private void checkIfBrandId(int brandId) throws BusinessException {
		if(!brandDao.getByBrandId(brandId)) {
			throw new BusinessException("Brand with id" + brandId + "is not exist");
		}

	}
}