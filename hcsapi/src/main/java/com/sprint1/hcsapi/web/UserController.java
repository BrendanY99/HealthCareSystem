package com.sprint1.hcsapi.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.hcsapi.domain.Users;
import com.sprint1.hcsapi.repository.UserRepository;
import com.sprint1.hcsapi.service.MapValidationErrorService;
import com.sprint1.hcsapi.service.UserService;

/**This is the UserController and it is used for handling front end calls and generate json response. 
 * @author devendra
 *
 */
@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewUser(@Valid@RequestBody Users users,BindingResult result){
		ResponseEntity<?> errormap =mapValidationErrorService.mapValidationError(result);
		if(errormap!=null) return errormap;
		Users savedUser=userService.saveOrUpdate(users);
		return new ResponseEntity<Users>(savedUser,HttpStatus.CREATED);
	}
	@GetMapping("/{email}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email){
		System.out.println(email);
		Users users = userService.findUserByEmail(email);
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Iterable<Users> getAllUsers(){
		return userService.findAll();
	}
	
	@DeleteMapping("/{email}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> removeUser(@PathVariable String email){
		userService.deleteUserByEmail(email);
		return new ResponseEntity<String>("User with email: "+email.toUpperCase()+ "is deleted ",HttpStatus.OK);
	}
	
	@PostMapping("/validate")
	public ResponseEntity<?> validateUser(@RequestBody Users users){
		return new ResponseEntity<String>(userService.validateUser(users.getUsername(), users.getPassword()),HttpStatus.OK);
	}
	
	

}
