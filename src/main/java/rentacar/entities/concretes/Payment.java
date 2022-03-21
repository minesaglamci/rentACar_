package rentacar.entities.concretes;

import java.time.LocalDate;

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
@Table(name = "payments")
@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private int paymentId;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "card_cvv")
	private String cardCvv;
	
	@Column(name = "card_owner_name")
	private String cardOwnerName;
	
	@Column(name = "card_expiration_date")
	private LocalDate cardExpirationDate;
	
	@ManyToOne()
	@JoinColumn(name = "rental_id")
	private Rental paymentRental;
	
	@JoinColumn(name = "invoice_id")
	private Invoice paymentInvoice;
}