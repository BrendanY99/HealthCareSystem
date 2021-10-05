package com.sprint1.hcsapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sprint1.hcsapi.configuration.JwtTokenProvider;
import com.sprint1.hcsapi.domain.Appointment;
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
			System.out.println(e);
			throw new EmailException("Email "+users.getEmail().toUpperCase()+" does not exists");
			
		}	
		
	}
	
	public String registerUser(Users users) {
		try {
			users.getRoles().add(Role.ROLE_USER);
			users.setEmail(users.getEmail().toUpperCase());
			users.setPassword(passwordEncoder.encode(users.getPassword()));
			Users user = userRepository.save(users);
			String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
			return token;
		}
		catch(Exception e) {
			System.out.println(e);
			throw new EmailException("Email "+users.getEmail().toUpperCase()+" already exists");
			
		}	
	}

	
	
	
	
	@Override
	public Users findUserByEmail(String email) {
		Users users =userRepository.findByEmail(email.toUpperCase());
		if(users==null) {
			throw new EmailException("Email :" +email.toUpperCase()+" does not exists");
		}
		return users;
	}

	@Override
	public Iterable<Users> findAll() {
		
		return userRepository.findAll();
	}

	@Override
	public void deleteUserByEmail(String email) {
		Users users=findUserByEmail(email.toUpperCase());
		userRepository.delete(users);	
		
	}

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
