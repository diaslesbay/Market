package com.example.test.service;

import com.example.test.dto.RefreshTokenRequest;
import com.example.test.dto.SignInRequest;
import com.example.test.dto.SignUpRequest;
import com.example.test.enums.TypeOfUser;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import com.example.test.repository.AuthenticationRepository;
import com.example.test.dto.JwtAuthenticationResponse;
import com.example.test.repository.JwtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationRepository {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtRepository jwtService;

    public User signup(SignUpRequest signUpRequest){
        User user = new User();
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setFirstname(signUpRequest.getFirstname());
        user.setLastname(signUpRequest.getLastname());
        user.setAddress(signUpRequest.getAddress());
        user.setEmail(signUpRequest.getEmail());
        user.setStatus(TypeOfUser.REGISTERED_USER);
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);
        return user;
    }
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        UserDetails user  = userRepository.findByUsername(signInRequest.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password!"));
        String jwt = jwtService.generateToken(user);
        System.out.println("signIn");
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setAccessToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String username = jwtService.extractUsername(refreshTokenRequest.getRefreshToken());
        User user = userRepository.findByUsername(username).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getRefreshToken(), user)){
            String jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setAccessToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getRefreshToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
