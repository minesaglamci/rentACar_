package rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.stereotype.Repository;
import rentacar.entities.concretes.AdditionalService;

@Repository
public interface AdditionalServiceDao {
	
	AdditionalService getByAdditionalServiceId(int additionalServiceId);
	
	List<AdditionalService> getAll();
	
	boolean existsByAdditionalServiceName(String additionalServiceName);

	boolean existsById(int additionalServiceId);

	void save(AdditionalService additionalService);

	void deleteById(int additionalServiceId);
}