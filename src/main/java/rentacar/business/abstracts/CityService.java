package rentacar.business.abstracts;

import java.util.List;


import rentacar.business.dtos.city.CityDto;
import rentacar.business.dtos.city.ListCityDto;
import rentacar.core.utilities.results.DataResult;

public interface CityService {

	DataResult<List<ListCityDto>> getAll();

	DataResult<CityDto> getByCityPlate(int cityPlate);

	DataResult<List<ListCityDto>> getAllPaged(int pageNo, int pageSize);
}