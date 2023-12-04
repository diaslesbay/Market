package com.example.test.validator;

import com.example.test.dto.RegisterDto;
import com.example.test.enums.ErrorMessage;
import com.example.test.exceptions.ServiceException;
import com.example.test.repository.UserRepository;
import com.example.test.service.SellerService;
import com.example.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SellerValidator{
    private final SellerService sellerService;
    private final UserService userService;
    public void validate(RegisterDto signUpRequest) {
        String username = signUpRequest.getUsername();
        String email = signUpRequest.getEmail();
        String phoneNumber = signUpRequest.getPhoneNumber();

        sellerService.findByUsernameWithoutThrow(username)
                .ifPresent(u -> throwUsernameAlreadyExistsException(username));

        userService.findByUsernameWithoutThrow(username)
                .ifPresent(u -> throwUsernameAlreadyExistsException(username));

        sellerService.getSellerByEmail(email)
                .ifPresent(u -> throwEmailAlreadyExistsException(email));

        sellerService.getSellerByPhoneNumber(phoneNumber)
                .ifPresent(u -> throwPhoneNumberAlreadyExistsException(phoneNumber));

    }

    private void throwUsernameAlreadyExistsException(String username) {
        throw new ServiceException(
                String.format(ErrorMessage.USERNAME_IS_ALREADY_EXIST.getMessage(), username),
                ErrorMessage.USERNAME_IS_ALREADY_EXIST.getStatus()
        );
    }

    private void throwEmailAlreadyExistsException(String email) {
        throw new ServiceException(
                String.format(ErrorMessage.EMAIL_IS_ALREADY_EXIST.getMessage(), email),
                ErrorMessage.EMAIL_IS_ALREADY_EXIST.getStatus()
        );
    }

    private void throwPhoneNumberAlreadyExistsException(String phoneNumber) {
        throw new ServiceException(
                String.format(ErrorMessage.PHONE_NUMBER_IS_ALREADY_EXIST.getMessage(), phoneNumber),
                ErrorMessage.PHONE_NUMBER_IS_ALREADY_EXIST.getStatus()
        );
    }
}
