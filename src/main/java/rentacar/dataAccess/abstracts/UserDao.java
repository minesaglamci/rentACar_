package rentacar.dataAccess.abstracts;

import org.springframework.stereotype.Repository;

import rentacar.entities.concretes.User;

@Repository
public interface UserDao {
	User getByUserId(int id);
}