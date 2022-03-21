package rentacar.dataAccess.abstracts;

import org.springframework.stereotype.Repository;

import rentacar.entities.concretes.Customer;

@Repository
public interface CustomerDao {
	Customer getByCustomerId(int customerId);
}