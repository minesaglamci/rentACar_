package rentacar.business.dtos.city;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {
	
	private int cityPlate;
	
	private String cityName;
}