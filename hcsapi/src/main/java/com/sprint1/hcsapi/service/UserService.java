package com.sprint1.hcsapi.service;

import com.sprint1.hcsapi.domain.Users;

/**This UserService interface have the functionality for Project Related Business logic
 * @author devendra
 *
 */

public interface UserService {
	
	/**This Update method will be used by user to update his details. 
	 * @param 
	 * @return the  updated user.
	 */
	public Users update(Users users);
	
	/**This method will register user if user is new.
	 * @param user
	 * @return the token.
	 */
	public String registerUser(Users user);
	
	
	/**This method will find the user according to their email. It will be used by admin.
	 * @param 
	 * @return the user or user not exists message will be displayed.
	 */
	public Users findUserByEmail(String email);
	
	/**
	 * @return This method will return  all the users and it is used by admin.
	 */
	public Iterable<Users> findAll();
	
	/**This method will delete the user according to their email. It will be used by admin.
	 * @param 
	 */
	public void deleteUserByEmail(String email);
	
	
	/**This method is used to login by user.
	 * @param username
	 * @param password
	 * @return the token.
	 */
	public String validateUser(String username,String password);
	


}
