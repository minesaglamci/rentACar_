package rentacar.business.dtos.carMaintenance;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rentacar.entities.concretes.Car;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceDto {

	private int carMaintenanceId;
	private String description;
	private LocalDate returnDate;
	private Car car;
	private int carId;
}