package rentacar.business.requests.rental;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	
	@NotNull
	@Min(value = 1)
	private int carId;
	
	@NotNull
	@Min(value = 1)
	private int rentalId;
	
	@NotNull
	@Min(value = 1)
	private int customerId;
	
	private String rentCity;
	
	private String returnCity;
	
	@NotEmpty
	private LocalDate rentDate;
	
	private LocalDate returnDate;

	@NotEmpty
	@Min(value = 1)
	private int rentCityId;

	@NotEmpty
	@Min(value = 1)
	private int returnCityId;

	private List<Integer> additionalServiceId;

}