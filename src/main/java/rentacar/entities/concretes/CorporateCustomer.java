package rentacar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="corporate_customers")
@Entity
@EqualsAndHashCode(callSuper=false)
@Inheritance(strategy= InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "corporate_customer_id", referencedColumnName = "customer_id")
public class CorporateCustomer extends Customer{
	
	@Column(name= "corporate_customer_id", insertable = false, updatable = false)
	private int corporateCustomerId;
	
	@Column(name = "company_name")
	private String companyName;

	@Column(name = "tax_number",unique = true)
	private String taxNumber;
}