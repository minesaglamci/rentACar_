package rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import rentacar.business.abstracts.BrandService;
import rentacar.business.dtos.brand.ListBrandDto;
import rentacar.business.requests.brand.CreateBrandRequest;
import rentacar.business.requests.brand.DeleteBrandRequest;
import rentacar.business.requests.brand.UpdateBrandRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	private BrandService brandService;

	@Autowired
	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}
	
    @GetMapping("/getAll")
    public DataResult<List<ListBrandDto>> getAll(){
        return this.brandService.getAll();
    }
    
    @GetMapping("/getId")
    public DataResult<ListBrandDto> getById(int brandId) throws BusinessException {
    	return this.brandService.getById(brandId);
    }
    @PostMapping("/add")
    public Result add(@RequestBody CreateBrandRequest createBrandRequest) throws BusinessException {
    	return this.brandService.add(createBrandRequest);
    }
    
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteBrandRequest deleteBrandRequest) throws BusinessException{
    	return this.brandService.delete(deleteBrandRequest);
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody UpdateBrandRequest updateBrandRequest) throws BusinessException{
    	return this.brandService.update(updateBrandRequest);
    }  
}