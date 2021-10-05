package com.sprint1.hcsapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sprint1.hcsapi.domain.Users;


/**This UserRepository interface is used to access all methods of crud repository
 * @author devendra
 *
 */
@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
  
	
	
	/**This method is used to find the user by email.
	 * @param email
	 * @return User object if user available otherwise exception is thrown.
	 */
	Users findByEmail(String email);
	
	/**This method is used to find user by username. 
	 * @param username
	 * @return the user
	 */
	Users findByUsername(String username);
	
  
}
