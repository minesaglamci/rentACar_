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
import rentacar.business.abstracts.ColorService;
import rentacar.business.dtos.color.ListColorDto;
import rentacar.business.requests.color.CreateColorRequest;
import rentacar.business.requests.color.DeleteColorRequest;
import rentacar.business.requests.color.UpdateColorRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {
	private ColorService colorService;

	@Autowired
	public ColorsController(ColorService colorService) {
		this.colorService = colorService;
	}
	
    @GetMapping("/getall")
    public DataResult<List<ListColorDto>> getAll(){
        return this.colorService.getAll();
    }
    
    @GetMapping("/getid")
    public DataResult<ListColorDto> getById(int colorId) throws BusinessException {
    	return this.colorService.getById(colorId);
    }
    @PostMapping("/add")
    public Result add(@RequestBody CreateColorRequest createColorRequest) throws BusinessException {
    	return this.colorService.add(createColorRequest);
    }
    
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteColorRequest deleteColorRequest) throws BusinessException{
    	return this.colorService.delete(deleteColorRequest);
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody UpdateColorRequest updateColorRequest) throws BusinessException{
    	return this.colorService.update(updateColorRequest);
    }  
}