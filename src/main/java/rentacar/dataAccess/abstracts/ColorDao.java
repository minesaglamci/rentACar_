package rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rentacar.entities.concretes.Color;

@Repository
public interface ColorDao extends JpaRepository<Color, Integer>{
	boolean getByColorName(String colorName);
	boolean getByColorId(int colorId);
}
