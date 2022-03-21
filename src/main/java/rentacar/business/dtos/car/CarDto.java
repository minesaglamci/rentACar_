package rentacar.business.dtos.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
	private int carId;
	private String carName;
	private double dailyPrice;
	private String modelYear;
	private String description;
	private String brandName;
	private String colorName;
}
