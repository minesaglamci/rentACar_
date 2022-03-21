package rentacar.business.dtos.additionalService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceDto {
	
	private int rentalId;
	private int additionalServiceId;
	private String additionalServiceName;
	private double dailyPrice;
	
}