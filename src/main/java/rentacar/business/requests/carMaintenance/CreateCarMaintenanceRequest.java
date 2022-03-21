package rentacar.business.requests.carMaintenance;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rentacar.entities.concretes.Car;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

	@NotNull
	private int carId;
	
	@NotNull
	private int carMaintenanceId;
	
	@NotNull
	@Size(min=2, max=100)
	private String description;
	
	@Nullable
	private LocalDate returnDate; 

}