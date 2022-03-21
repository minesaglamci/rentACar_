package rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rentacar.business.abstracts.CityService;
import rentacar.business.dtos.city.CityDto;
import rentacar.business.dtos.city.ListCityDto;
import rentacar.core.utilities.results.DataResult;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

	private CityService cityService;

	@Autowired
	public CitiesController(CityService cityService) {
		
		this.cityService = cityService;
	}

	@GetMapping("/getAll")
	public DataResult<List<ListCityDto>> listAll(){
		
		return this.cityService.listAll();
	}

	@GetMapping("/getByCityPlate")
	DataResult<CityDto> getByCityPlate(int cityPlate){
		
		return this.cityService.getByCityPlate(cityPlate);
	}


	@GetMapping("/getAllPaged")
	public DataResult<List<ListCityDto>> getAllPaged(int pageNo, int pageSize){
		
		return this.cityService.getAllPaged(pageNo, pageSize);
	}
}