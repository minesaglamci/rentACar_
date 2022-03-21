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
import rentacar.business.abstracts.RentalService;
import rentacar.business.dtos.rental.ListRentalDto;
import rentacar.business.dtos.rental.RentalDto;
import rentacar.business.requests.rental.CreateRentalRequest;
import rentacar.business.requests.rental.UpdateRentalRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
	private final RentalService rentalService;
	
	@Autowired
	public RentalsController(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@GetMapping("/getAll")
	public DataResult<List<ListRentalDto>> getAll(){
		return this.rentalService.getAll();
	}
	
	@GetMapping("/getByRentalId")
    public DataResult<RentalDto> getByRentalId(int rentalId) throws BusinessException {
    	return this.rentalService.getById(rentalId);
    }
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid  CreateRentalRequest createRentalRequest) throws BusinessException{
		return this.rentalService.create(createRentalRequest);	
	}
	
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) throws BusinessException{
    	return this.rentalService.update(updateRentalRequest);
    }
    
}