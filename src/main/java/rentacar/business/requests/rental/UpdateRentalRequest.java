package rentacar.business.requests.rental;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	
	@NotNull
	@Min(value = 1)
	private int carId;
	
	@NotNull
	@Min(value = 1)
	private int rentalId;
	
	@NotNull
	@Min(value = 1)
	private int customerId;
	
	@NotNull
	private LocalDate rentDate;
	
	@NotNull
	private LocalDate returnDate;
	
	@NotNull
	private LocalDate returnKilometer;
	
	@NotNull
	@Min(value = 1)
	private int rentCityId;
	
	@NotNull
	@Min(value = 1)
	private int returnCityId;
}