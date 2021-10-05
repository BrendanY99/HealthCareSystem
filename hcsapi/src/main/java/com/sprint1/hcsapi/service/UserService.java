package com.sprint1.hcsapi.service;

import com.sprint1.hcsapi.domain.Appointment;
import com.sprint1.hcsapi.domain.Users;

/**This UserService interface have the functionality for Project Related Business logic
 * @author devendra
 *
 */

public interface UserService {
	
	/**This saveOrUpdate method will save the User if id not provided otherwise update. 
	 * @param user to be saved or updated.
	 * @return the Saved or updated user.
	 */
	public Users update(Users users);
	
	public String registerUser(Users user);
	
	
	/**This method will find the user according to their email
	 * @param 
	 * @return the user or user not exists message will be displayed.
	 */
	public Users findUserByEmail(String email);
	
	/**
	 * @return This method will return  all the users.
	 */
	public Iterable<Users> findAll();
	
	/**This method will delete the user according to their email
	 * @param 
	 */
	public void deleteUserByEmail(String email);
	
	
	/**
	 * @param email
	 * @param password
	 * @return
	 */
	public String validateUser(String username,String password);
	


}
