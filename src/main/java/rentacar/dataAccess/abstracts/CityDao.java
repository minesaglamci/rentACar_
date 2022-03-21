package rentacar.dataAccess.abstracts;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import rentacar.entities.concretes.City;

@Repository
public interface CityDao {

	City findByCityPlate(int cityPlate);
	
	boolean existsByCityName(String cityName);
	
	City getByCityName(int cityName);

	City getById(int cityPlate);

	City findAll(Pageable pageable);
}