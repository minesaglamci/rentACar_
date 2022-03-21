package rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.var;
import rentacar.business.abstracts.CityService;
import rentacar.business.dtos.brand.ListBrandDto;
import rentacar.business.dtos.city.CityDto;
import rentacar.business.dtos.city.ListCityDto;
import rentacar.core.utilities.mapping.ModelMapperService;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.SuccessDataResult;
import rentacar.dataAccess.abstracts.CityDao;
import rentacar.entities.concretes.City;

@Service
public class CityManager implements CityService{

	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	private CityService cityService;
	
	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService, CityService cityService) {
		
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
		this.cityService = cityService;
	}
	
	@Override
	public DataResult<List<ListCityDto>> getAll() {
		
		var result = this.cityService.getAll();
		
		List<ListCityDto> response = result.stream().map(city -> this.modelMapperService.forDto().map(city, ListCityDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCityDto>>(response);
	}
	@Override
	public DataResult<CityDto> getByCityPlate(int cityPlate) {
		
		City city = this.cityDao.getById(cityPlate);
		CityDto cityDto = this.modelMapperService.forDto().map(city, CityDto.class);
		
		return new SuccessDataResult<CityDto>(cityDto, "Data getted by id");
	}

	@Override
	public DataResult<List<ListCityDto>> getAllPaged(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}


}
