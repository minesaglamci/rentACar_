package rentacar.business.dtos.rental;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rentacar.entities.concretes.Car;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalDto {
	
	private int rentalId;
	private int customerId;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private Car car;
	
}