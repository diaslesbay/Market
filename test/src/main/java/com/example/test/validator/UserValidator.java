package com.example.test.validator;

import com.example.test.dto.SignInRequest;
import com.example.test.dto.SignUpRequest;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {
    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }


    @Override
    public void validate(Object target, Errors errors) {
        SignUpRequest signUpRequest = (SignUpRequest) target;

        if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent())
            errors.rejectValue("username", "", "User with that name already exists");
         else if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent())
            errors.rejectValue("email", "", "User with that email already exists");
         else if (userRepository.findByPhoneNumber(signUpRequest.getPhoneNumber()).isPresent())
            errors.rejectValue("phoneNumber", "", "User with that phone number already exists");

    }

}
