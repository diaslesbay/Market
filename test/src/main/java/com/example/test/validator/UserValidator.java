package com.example.test.validator;

import com.example.test.dto.RegisterDto;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.springframework.validation.Validator;
@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }


    @Override
    public void validate(Object target, Errors errors) {
        RegisterDto signUpRequest = (RegisterDto) target;
        var a = userRepository.findByUsername(signUpRequest.getUsername()).isPresent();
        var b = userRepository.findByEmail(signUpRequest.getEmail()).isPresent();
        var c = userRepository.findByPhoneNumber(signUpRequest.getPhoneNumber()).isPresent();

        if (a == true)
            errors.rejectValue("username", "", "User with that name already exists");
        else if (b == true)
            errors.rejectValue("email", "", "User with that email already exists");
        else if (c == true)
            errors.rejectValue("phoneNumber", "", "User with that phone number already exists");

    }

}
