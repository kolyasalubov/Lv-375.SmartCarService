package ua.ita.smartcarservice.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.ita.smartcarservice.dto.UserDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.dto.booking.WorkerDto;
import ua.ita.smartcarservice.dto.booking.WorkerWithSkillDto;
import ua.ita.smartcarservice.service.impl.UserServiceImpl;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
		UserDto userDto;
		try {
			userDto = userService.getUserById(id);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/users/all")
	public ResponseEntity<List<UserDto>> findAll() {
		List<UserDto> users = userService.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/user/{username}")
	public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
		UserDto userDto;
		try {
			userDto = userService.findByUsername(username);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/user/{id}")
	public ResponseEntity deleteUser(@PathVariable Long id) {
		try {
			List<UserDto> users = userService.findAll();
			for (UserDto u : users) {
				if (u.getId() == id) {
					userService.deleteById(id);
					return new ResponseEntity<>(HttpStatus.OK);
				}

			}
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/newuser")
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity){
		userService.createUser(userEntity);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/userchange/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable("userId")Long id, UserEntity userEntity){
		userService.updateUserById(id, userEntity);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}

}
