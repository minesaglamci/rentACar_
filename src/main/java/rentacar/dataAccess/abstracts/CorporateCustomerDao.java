package rentacar.dataAccess.abstracts;

import org.springframework.stereotype.Repository;

import rentacar.entities.concretes.CorporateCustomer;

@Repository
public interface CorporateCustomerDao {
	
	CorporateCustomer getByCorporateCustomerId(int corporateCustomerId);
	
	CorporateCustomer findByEmail(String email);
	
	CorporateCustomer findByTaxNumber(String taxNumber);
}