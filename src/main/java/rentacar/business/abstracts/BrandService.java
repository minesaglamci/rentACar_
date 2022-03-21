package rentacar.business.abstracts;

import java.util.List;

import rentacar.business.dtos.brand.ListBrandDto;
import rentacar.business.requests.brand.CreateBrandRequest;
import rentacar.business.requests.brand.DeleteBrandRequest;
import rentacar.business.requests.brand.UpdateBrandRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;

public interface BrandService {
	
	Result add(CreateBrandRequest createBrandRequest) throws BusinessException;
	
	Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException;
	
	Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException;
	
	DataResult<List<ListBrandDto>> getAll();
	
	DataResult<ListBrandDto> getById(int brandId) throws BusinessException;
}
