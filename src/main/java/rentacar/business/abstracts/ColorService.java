package rentacar.business.abstracts;

import java.util.List;

import rentacar.business.dtos.color.ListColorDto;
import rentacar.business.requests.color.CreateColorRequest;
import rentacar.business.requests.color.DeleteColorRequest;
import rentacar.business.requests.color.UpdateColorRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;

public interface ColorService {
	
	Result add(CreateColorRequest createColorRequest) throws BusinessException;
	
	Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException;
	
	Result update(UpdateColorRequest updateColorRequest) throws BusinessException;
	
	DataResult<List<ListColorDto>> getAll();
	
	DataResult<ListColorDto> getById(int colorId) throws BusinessException;
}