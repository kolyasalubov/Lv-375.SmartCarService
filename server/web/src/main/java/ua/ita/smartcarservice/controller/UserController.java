package ua.ita.smartcarservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.UserDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.service.UserService;


@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    /* Get user by id */
    @GetMapping("/userbyid/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /* Get all users */
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /*Get user by username */
    @GetMapping("/userbyname/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
        UserDto userDto = userService.findByUsername(username);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /* Delete user by id */
    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/newuser")
    public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
        userService.createUser(userEntity);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    @PostMapping("/userchange/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long id, UserEntity userEntity) {
        userService.updateUserById(id, userEntity);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}