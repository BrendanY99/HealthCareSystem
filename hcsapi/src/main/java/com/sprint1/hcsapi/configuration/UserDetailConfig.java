package com.sprint1.hcsapi.configuration;

import java.security.Security;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sprint1.hcsapi.domain.Users;
import com.sprint1.hcsapi.repository.UserRepository;

@Service
public class UserDetailConfig implements UserDetailsService{
	@Autowired
	private UserRepository userRepository ;
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
