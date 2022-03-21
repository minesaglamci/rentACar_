package rentacar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="rentals")
@Entity
public class Rental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rental_id")
	private int rentalId;
	
	@Column (name = "customer_id")
	private int customerId;
	
	@Column (name = "rent_date")
	private LocalDate rentDate;
	
	@Column (name = "return_date")
	private LocalDate returnDate;
	
	@Column (name = "return_kilometer")
	private LocalDate returnKilometer;
	
	@ManyToOne
	@JoinColumn (name = "car_id")
	private Car cars;
	
	@ManyToOne
	@JoinColumn(name = "rent_city")
	private City rentCity;

	@ManyToOne
	@JoinColumn(name = "return_city")
	private City returnCity;
	
	
	@OneToMany(mappedBy = "rental")
	private List<AdditionalService> additionalServices;


	@Column(name= "total_price")
	public double totalPrice;


	
}