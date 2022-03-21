package rentacar.dataAccess.abstracts;

import org.springframework.stereotype.Repository;

import rentacar.entities.concretes.IndividualCustomer;

@Repository
public interface IndividualCustomerDao {
	
	IndividualCustomer getByIndividualCustomerId(int individualCustomerId);
	
	IndividualCustomer findByEmail(String email);
	
	IndividualCustomer findByIdentityNumber(String identityNmuber);
}