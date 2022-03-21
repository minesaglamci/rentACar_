package rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rentacar.entities.concretes.Car;

@Repository
public interface CarDao extends JpaRepository <Car, Integer>{
	boolean getByCarId(int carId);
	boolean getByCarName(String carName);
	List<Car> getByDailyPriceLessThanEqual (double dailyPrice);
}
