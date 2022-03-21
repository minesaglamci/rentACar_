package rentacar.business.dtos.rental;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rentacar.business.dtos.additionalService.ListAdditionalServiceDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {

	private int rentalId;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private String rentCityName;
	private String returnCityName;
	private List<ListAdditionalServiceDto> additionalServices;
	private double rentalDailyPrice;
	private int customerId;
	private int carId;

}