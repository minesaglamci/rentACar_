package rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rentacar.entities.concretes.Brand;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer>{
	boolean getByBrandName(String brandName);
	boolean getByBrandId (int brandId);
	String getBrandName(String brandName);
}
