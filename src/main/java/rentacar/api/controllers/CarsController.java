package rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import rentacar.business.abstracts.CarService;
import rentacar.business.dtos.car.CarDto;
import rentacar.business.dtos.car.GetCarByDailyPriceDto;
import rentacar.business.dtos.car.ListCarDto;
import rentacar.business.requests.car.CreateCarRequest;
import rentacar.business.requests.car.DeleteCarRequest;
import rentacar.business.requests.car.UpdateCarRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	private CarService carService;

	@Autowired
	public CarsController(CarService carService) {
		this.carService = carService;
	}
	
    @GetMapping("/getAll")
    public DataResult<List<ListCarDto>> getAll(){
        return this.carService.getAll();
    }
    
    @GetMapping("/getId")
    public DataResult<CarDto> getById(int carId) throws BusinessException {
    	return this.carService.getById(carId);
    }
    @PostMapping("/add")
    public Result add(@RequestBody CreateCarRequest createCarRequest) throws BusinessException {
    	return this.carService.create(createCarRequest);
    }
    
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) throws BusinessException{
    	return this.carService.delete(deleteCarRequest);
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody UpdateCarRequest updateCarRequest) throws BusinessException{
    	return this.carService.update(updateCarRequest);
    }  
    
	@GetMapping("/getAllPaged")
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize){
		return this.carService.getAllPaged(pageNo, pageSize);
	}
	
	@GetMapping("/getAllSorted")
	public DataResult<List<ListCarDto>> getAllSorted(){
		return this.carService.getAllSorted();
	}
	
	@GetMapping("/getCarByDailyPrice")
	DataResult <List<GetCarByDailyPriceDto>> getCarByDailyPriceLessThanEqual(@Valid double dailyPrice){
		return this.carService.getCarByDailyPriceLessThanEqual(dailyPrice);
	}
}