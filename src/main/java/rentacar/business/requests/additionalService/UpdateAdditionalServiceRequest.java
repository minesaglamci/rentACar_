package rentacar.business.requests.additionalService;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {
	
    @NotNull
    @Min(0)
    @Max(50)
    private int additionalServiceId;
    
    @NotNull
    @Size(min = 2,max = 50)
    private String additionalServiceName;
    
    @NotNull
    @Min(0)
    @Max(100)
    private double additionalServicePrice;
    
    @NotNull
    @Size(min = 2,max = 250)
    private String additionalServiceDescription;
}