package rentacar.entities.concretes;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="additional_services")
@Entity
public class AdditionalService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "additional_service_id")
	private int addtionalServiceId;

	@Column(name = "additional_service_name")
	private String additionalServiceName;

	@Column(name = "additional_service_description")
	private String additionalServiceDescription;
	
	@Column(name = "additional_service_price")
	private double additionalServicePrice;

	@ManyToOne
	@JoinColumn(name = "rental_id")
	private Rental rental;
}