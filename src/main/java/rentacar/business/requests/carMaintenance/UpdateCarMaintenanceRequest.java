package rentacar.business.requests.carMaintenance;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {
	
	@NotNull
	private int carMaintenanceId;

	@NotNull
	private int carId;

	@NotNull
	@Size(min = 2, max = 150)
	private String description;

	@NotNull
	private LocalDate maintenanceDate;
	
	@Nullable
	private LocalDate returnDate;

}