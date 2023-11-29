package com.example.test.validator;

import com.example.test.dto.RegisterDto;
import com.example.test.model.Seller;
import com.example.test.repository.SellerRepository;
import com.example.test.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SellerValidator implements Validator {
    private final SellerService sellerService;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Seller.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterDto signUpRequest = (RegisterDto) target;
        var a = sellerService.getSellerByUsername(signUpRequest.getUsername()).isPresent();
        var b = sellerService.getSellerByEmail(signUpRequest.getEmail()).isPresent();
        var c = sellerService.getSellerByPhoneNumber(signUpRequest.getPhoneNumber()).isPresent();

        if (a == true)
            errors.rejectValue("username", "", "Seller with that name already exists");
        else if (b == true)
            errors.rejectValue("email", "", "Seller with that email already exists");
        else if (c == true)
            errors.rejectValue("phoneNumber", "", "Seller with that phone number already exists");

    }
}
