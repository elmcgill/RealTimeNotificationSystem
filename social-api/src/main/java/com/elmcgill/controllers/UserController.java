package com.elmcgill.controllers;

import com.elmcgill.dto.LoginUserDTO;
import com.elmcgill.dto.RegisterUserDTO;
import com.elmcgill.exceptions.InvalidCredentialsException;
import com.elmcgill.exceptions.UnableToSaveUserException;
import com.elmcgill.models.User;
import com.elmcgill.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterUserDTO registerObject) {
        return userService.createUser(registerObject);
    }

    @ExceptionHandler({UnableToSaveUserException.class})
    public ResponseEntity<String> handleUnableToSaveUser(){
        return new ResponseEntity<>("Unable able to save the user", HttpStatus.CONFLICT);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody LoginUserDTO loginObject) {
        return userService.getUserByEmailAndPassword(loginObject);
    }

    @ExceptionHandler({InvalidCredentialsException.class})
    public ResponseEntity<String> handleInvalidCredentialsException(){
        return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }



}
