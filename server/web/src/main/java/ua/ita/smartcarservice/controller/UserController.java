package ua.ita.smartcarservice.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.UserDto;
import ua.ita.smartcarservice.dto.feedback.ServicesFeedbackOutputDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.feedback.ServicesFeedbackService;


@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    ServicesFeedbackService servicesFeedbackService;

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    /* Get user by id */
    @GetMapping("/userbyid/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /* Get all users */
    @GetMapping("/users/all")
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


    @GetMapping("/api/users/{userName}/feedback")
    ResponseEntity<List<ServicesFeedbackOutputDto>> getAllUsersFeedback(@PathVariable String userName) {
        ResponseEntity<List<ServicesFeedbackOutputDto>> responseEntity;

        try {
            responseEntity = new ResponseEntity<List<ServicesFeedbackOutputDto>>(
                    servicesFeedbackService.getUsersFeedback(userName), HttpStatus.OK);
            logger.info("Successfully got feedback from User by username: " + userName);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            logger.warn("Can not get feedback from User by username: " + userName + " Details: " + e.getMessage());
        }

        return responseEntity;
    }
}