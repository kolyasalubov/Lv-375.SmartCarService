package ua.ita.smartcarservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity){
		
		userService.createUser(userEntity);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping("/users/all")
	public ResponseEntity<List<UserEntity>> getAllUsers(){
		
		List<UserEntity> users = userService.findAll();
		
		return new ResponseEntity<List<UserEntity>>(users, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserEntity> findUserById(@PathVariable("userId")Long id){
		
		UserEntity user = userService.findById(id);
		
		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
	}
	
	@PostMapping("/user/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable("userId")Long id, UserEntity userEntity){
		
		userService.updateUserById(id, userEntity);
		
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long id, UserEntity userEntity){
		
		userService.deleteById(id, userEntity);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}

}
