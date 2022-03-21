package rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.var;
import rentacar.business.abstracts.ColorService;
import rentacar.business.dtos.color.ListColorDto;
import rentacar.business.requests.color.CreateColorRequest;
import rentacar.business.requests.color.DeleteColorRequest;
import rentacar.business.requests.color.UpdateColorRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.mapping.ModelMapperService;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;
import rentacar.core.utilities.results.SuccessDataResult;
import rentacar.core.utilities.results.SuccessResult;
import rentacar.dataAccess.abstracts.ColorDao;
import rentacar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListColorDto>> getAll() {
		var result = this.colorDao.findAll();
		List<ListColorDto> response = result.stream()
				.map(color -> this.modelMapperService.forDto().map(color, ListColorDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListColorDto>>(response, "Succeeded!");
	}

	@Override
	public DataResult<ListColorDto> getById(int colorId) throws BusinessException {
		Color result = this.colorDao.getById(colorId);
		if (result != null) {
			ListColorDto response = this.modelMapperService.forDto().map(result, ListColorDto.class);
			return new SuccessDataResult<ListColorDto>(response, "Succeeded!");
		}
		throw new BusinessException("Brand was not found!");
	}
	
	@Override
	public Result add(CreateColorRequest createColorRequest) throws BusinessException {
		checkColorNames(createColorRequest.getColorName());
		
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorDao.save(color);
		
		return new SuccessResult("Color is added:" + createColorRequest.getColorName());
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException {
		
		checkIfColorId(deleteColorRequest.getColorId());
		
		this.colorDao.deleteById(deleteColorRequest.getColorId());
		
		return new SuccessResult("Color is deleted ");
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) throws BusinessException {
		
		checkIfColorId(updateColorRequest.getColorId());
		checkColorNames(updateColorRequest.getColorName());
		
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(color);
		
		return new SuccessDataResult<UpdateColorRequest>(updateColorRequest,
				"Color is updated : " + color.getColorName());
	}
	
	private void checkColorNames(String colorName) throws BusinessException {
		if (!colorDao.getByColorName(colorName)) {
			throw new BusinessException("Color with name" + colorName + "is not exist");
		} 
	}
	
	private void checkIfColorId(int colorId) throws BusinessException {
		if(!colorDao.getByColorId(colorId)) {
			throw new BusinessException("Color is not exist");
		}

	}
}