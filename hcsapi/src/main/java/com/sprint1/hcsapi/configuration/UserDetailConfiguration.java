package com.sprint1.hcsapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.sprint1.hcsapi.domain.Users;
import com.sprint1.hcsapi.repository.UserRepository;

/**
 * This UserDetailConfig class is used for accessing the username and password of users.
 * @author devendra
 *
 */
@Service
public class UserDetailConfiguration implements UserDetailsService{
	@Autowired
	private UserRepository userRepository ;
	/**
	 * This method checks if user exists with provided username.
	 * The token will be provided if provided  password and username are correct.
	 * 
	 */
	@Override
	public UserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users users = userRepository.findByUsername(username);
		if(users==null) {
			throw new UsernameNotFoundException("username not found");
		}
		return org.springframework.
				security.core.userdetails.User.withUsername(username)
				.password(users.getPassword())
				.authorities(users.getRoles())
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
	}
	   
}
