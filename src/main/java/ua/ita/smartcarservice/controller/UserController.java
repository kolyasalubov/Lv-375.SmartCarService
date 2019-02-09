package ua.ita.smartcarservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping
	public ResponseEntity<List<UserEntity>> getAllUsers(){
		
		List<UserEntity> users = userService.findAll();
		
		return new ResponseEntity<List<UserEntity>>(users, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserEntity> findUserById(@PathVariable("userId")Long id){
		
		UserEntity user = userService.findById(id);
		
		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
	}
	
	@PostMapping("/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable("userId")Long id, UserEntity userEntity){
		
		userService.updateUserById(id, userEntity);
		
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long id, UserEntity userEntity){
		
		userService.deleteById(id, userEntity);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}

}
