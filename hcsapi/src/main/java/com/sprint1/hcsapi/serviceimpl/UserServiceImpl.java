package com.sprint1.hcsapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sprint1.hcsapi.configuration.JwtTokenProvider;
import com.sprint1.hcsapi.domain.Role;
import com.sprint1.hcsapi.domain.Users;
import com.sprint1.hcsapi.exception.EmailException;
import com.sprint1.hcsapi.repository.UserRepository;
import com.sprint1.hcsapi.service.UserService;

/**This class UserServiceImpl will be implementing methods of UserService interface.
 * @author devendra
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
/**
 * This method is overriding the update method of userService.
 * This method will check if user id is not null and user exists then only update will get executed. 
 * If any unique constraint violation take place then exception will be thrown.
 * User cannot update his username and email.
 */
	@Override
	public Users update(Users users) {
		try {
			users.getRoles().add(Role.ROLE_USER);
			if(users.getId()!=null && userRepository.existsById(users.getId()) ) 
			{
				Users oldUser = userRepository.findById(users.getId()).get();
				if(users.getName()!=null) {
					oldUser.setName(users.getName());
				}
				if(users.getAge()!=null) {
					oldUser.setAge(users.getAge());
				}
	            if(users.getPhoneNo()!=null) {
	            	oldUser.setPhoneNo(users.getPhoneNo());
	            }
	            if(users.getPassword()!=null) {
	            	oldUser.setPassword(passwordEncoder.encode(users.getPassword()));
	            }
				return userRepository.save(oldUser);
			}
			else {
				throw new Exception();
			}
		
	  }catch(Exception e) {
			throw new EmailException("Cannot update this information.");
			
		}		
	}
	/**
	 *This method is overriding the registerUser method of userService.
	 *This method will be used by user to register himself when user is new.
	 *This method will check if this user exists already or not then only user gets register.
	 */
	@Override
	public String registerUser(Users users) {
		try {
			
		         users.getRoles().add(Role.ROLE_USER);
		         users.setEmail(users.getEmail().toUpperCase());
		         users.setPassword(passwordEncoder.encode(users.getPassword()));
		         Users user = userRepository.save(users);
		         String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
		         return token;
		     
	    }catch(Exception e) {
		 throw new EmailException("User with this Email "+users.getEmail().toUpperCase()+" already exists");
	     }
	}
	
	/**
	 * This method is overriding the findUserByEmail method of userService.
	 * 
	 */
	@Override
	public Users findUserByEmail(String email) {
		Users users =userRepository.findByEmail(email.toUpperCase());
		if(users==null) {
			throw new EmailException("Email :" +email.toUpperCase()+" does not exists");
		}
		return users;
	}
	
    /**
     * This method is overriding the findAll method of userService.
     */
	@Override
	public Iterable<Users> findAll() {
		
		return userRepository.findAll();
	}
    /**
     * This method is overriding the deleteUserByEmail method of userService.
     */
	@Override
	public void deleteUserByEmail(String email) {
		Users users=findUserByEmail(email.toUpperCase());
		userRepository.delete(users);	
		
	}
    
	/**
	 * This method is overriding the validateUser method of userService.
	 * It will return token is given username and password is correct.
	 * It is used for login purpose.
	 */
	@Override
	public String validateUser(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			String token = jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
			return token;
		}catch(Exception e){
			System.out.println(e);
			throw new EmailException("validation failed");
		}
		
	}

}
