package com.sprint1.hcsapi.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.hcsapi.domain.Users;
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
	/**
	 * This api will be used by user to register himself for first time, This api does not require authorization. 
	 * @param It is using the registerUser method from userService.
	 * @return The token which will be used for authorization purpose.
	 */
	@PostMapping("/register")
	public ResponseEntity<?> createNewUser(@Valid@RequestBody Users users,BindingResult result){
		ResponseEntity<?> errormap =mapValidationErrorService.mapValidationError(result);
		if(errormap!=null) return errormap;
		String token=userService.registerUser(users);
		return new ResponseEntity<String>(token,HttpStatus.CREATED);
	}
	
	/**
	 * This api will be used by user and admin both to update their personal details.
	 * @param It is using the updateUser method from userService.
	 * @return The user with updated details.
	 */
	@PostMapping("/update")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateUser(@Valid@RequestBody Users users,BindingResult result){
		ResponseEntity<?> errormap =mapValidationErrorService.mapValidationError(result);
		if(errormap!=null) return errormap;
		Users savedUser=userService.update(users);
		return new ResponseEntity<Users>(savedUser,HttpStatus.OK);
	}
	
	/**
	 * This api will be used by admin to get the details of particular used using his email.
	 * @param It is using the getUserByEmail method from userService.
	 * @return The user having this email.
	 */
	@GetMapping("/{email}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email){
		System.out.println(email);
		Users users = userService.findUserByEmail(email);
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	
	/**
	 * This api will be used by admin to get the details of all users..
	 * It is using the getAllUsers method from userService.
	 * @return The all users.
	 */
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Iterable<Users> getAllUsers(){
		return userService.findAll();
	}
	/**
	 * This api will be used by admin to remove user based on his email.
	 * @param It is using the removeUser method from userService.
	 * @return The deletion status.
	 */
	@DeleteMapping("/{email}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> removeUser(@PathVariable String email){
		userService.deleteUserByEmail(email);
		return new ResponseEntity<String>("User with email: "+email.toUpperCase()+ "is deleted ",HttpStatus.OK);
	}
	
	/**
	 * This api will be used by user and admin for login purpose. This api does not require authorization.
	 * @param It is using the validateUser method from userService.
	 * @return The token which will be used for authorization purpose.
	 */
	@PostMapping("/validate")
	public ResponseEntity<?> validateUser(@RequestBody Users users){
		return new ResponseEntity<String>(userService.validateUser(users.getUsername(), users.getPassword()),HttpStatus.OK);
	}
}
