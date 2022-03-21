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
import rentacar.business.abstracts.AdditionalServiceService;
import rentacar.business.dtos.additionalService.AdditionalServiceDto;
import rentacar.business.dtos.additionalService.ListAdditionalServiceDto;
import rentacar.business.requests.additionalService.CreateAdditionalServiceRequest;
import rentacar.business.requests.additionalService.UpdateAdditionalServiceRequest;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalServices")
public class AdditionalServicesController {

	private AdditionalServiceService additionalServiceService;

	@Autowired
	public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
		
		this.additionalServiceService = additionalServiceService;
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest){
		
		return this.additionalServiceService.update(updateAdditionalServiceRequest);
	}

	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest){
		
		return this.additionalServiceService.create(createAdditionalServiceRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<ListAdditionalServiceDto>> listAll(){
		
		return this.additionalServiceService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<AdditionalServiceDto> getById(@RequestParam int additionalServiceId){
		
		return this.additionalServiceService.getById(additionalServiceId);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestParam int additionalServiceId){
		
		return this.additionalServiceService.delete(additionalServiceId);
	}
}