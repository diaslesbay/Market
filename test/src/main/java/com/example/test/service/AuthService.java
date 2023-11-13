package com.example.test.service;

import com.example.test.dto.JwtRequest;
import com.example.test.dto.JwtResponse;
import com.example.test.exceptions.AppError;
import com.example.test.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            System.out.println("createAuthToken");
            System.out.println(authRequest.getUsername()+ " "+authRequest.getPassword());
            var v = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            System.out.println(v.getAuthorities());

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
