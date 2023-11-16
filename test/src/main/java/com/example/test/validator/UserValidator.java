package com.example.test.validator;

import com.example.test.model.User;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userService.findByUsername(user.getUsername()).isPresent())
            errors.rejectValue("username", "", "User with that name already exists");
         else if (userService.findByEmail(user.getEmail()).isPresent())
            errors.rejectValue("email", "", "User with that email already exists");
         else if (userService.findByPhoneNumber(user.getPhoneNumber()).isPresent())
            errors.rejectValue("phoneNumber", "", "User with that phone number already exists");

    }

}
