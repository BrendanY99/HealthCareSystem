
package com.sprint1.hcsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sprint1.hcsapi.domain.Role;
import com.sprint1.hcsapi.domain.Users;
import com.sprint1.hcsapi.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class InitialAdminSetup implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception{
		Users users = userRepository.findByUsername("admin");
		if(users!=null){
			return;
		}
		users = new Users();
		users.getRoles().add(Role.ROLE_ADMIN);
		users.getRoles().add(Role.ROLE_USER);
		users.setName("admin");
		users.setUsername("admin");
		users.setEmail("admin@something.com");     
		users.setPassword(passwordEncoder.encode("admin@123"));
		users.setGender("Male");
		users.setPhoneNo("9999999999");
		users.setAge(40);
		userRepository.save(users);
	}
}
