package com.elmcgill.services;

import com.elmcgill.dto.LoginUserDTO;
import com.elmcgill.dto.RegisterUserDTO;
import com.elmcgill.exceptions.InvalidCredentialsException;
import com.elmcgill.exceptions.UnableToSaveUserException;
import com.elmcgill.exceptions.UserDoesNotExistException;
import com.elmcgill.models.User;
import com.elmcgill.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(RegisterUserDTO ro) {
        User userToRegister = new User(
                0L,
                ro.firstName(),
                ro.lastName(),
                ro.email(),
                ro.password(),
                ro.username(),
                new HashSet<>(),
                new HashSet<>());

        try{

            return userRepository.save(userToRegister);

        } catch(Exception e){
            throw new UnableToSaveUserException(e.getMessage());
        }
    }

    public User getUserByEmailAndPassword(LoginUserDTO lo) {
        return userRepository.findByEmailAndPassword(lo.email(), lo.password()).orElseThrow(InvalidCredentialsException::new);
    }

    public User getUserById (Long id) {
        return userRepository.findById(id).orElseThrow(UserDoesNotExistException::new);
    }


}
