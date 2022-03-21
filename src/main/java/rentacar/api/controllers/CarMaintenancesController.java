package rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import rentacar.business.abstracts.CarMaintenanceService;
import rentacar.business.dtos.carMaintenance.CarMaintenanceDto;
import rentacar.business.dtos.carMaintenance.ListCarMaintenanceDto;
import rentacar.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import rentacar.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/carMaintenances")
public class CarMaintenancesController {
	private CarMaintenanceService carMaintenanceService;

	@Autowired
	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		this.carMaintenanceService = carMaintenanceService;
	}

	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest)
			throws BusinessException {
		return this.carMaintenanceService.create(createCarMaintenanceRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestParam("carMaintenanceId") int carMaintenanceId, @RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest)
			throws BusinessException {
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestParam("carMaintenanceId") int carMaintenanceId)
			throws BusinessException {
		return this.carMaintenanceService.delete(carMaintenanceId);
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		return this.carMaintenanceService.getAll();
	}

	@GetMapping("/getid/{carId}")
	public DataResult<List<ListCarMaintenanceDto>> getByCarId(@RequestParam("carId") @Valid int carId)
			throws BusinessException {
		return this.carMaintenanceService.getCarMaintenanceByCarId(carId);
	}
	
	@GetMapping("/getid/{carMaintenanceId}")
	public DataResult<CarMaintenanceDto> getById(@RequestParam("carMaintenanceId") @Valid int carMaintenanceId)
			throws BusinessException {
		return this.carMaintenanceService.getById(carMaintenanceId);
	}
	
	@GetMapping("/getCarMaintenanceByCarId/{carId}")
	DataResult<List<ListCarMaintenanceDto>> getCarMaintenanceByCarId(@RequestParam("carId") @Valid int carId)
			throws BusinessException {
		return this.carMaintenanceService.getCarMaintenanceByCarId(carId);
	}
}