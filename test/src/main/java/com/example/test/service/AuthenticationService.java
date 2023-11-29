package com.example.test.service;

import com.example.test.exceptions.EntityNotFoundException;
import com.example.test.exceptions.JwtIsInvalid;
import com.example.test.exceptions.JwtTokenExpiredException;
import com.example.test.model.Seller;
import com.example.test.token.JwtParser;
import com.example.test.token.validator.JwtValidator;
import com.example.test.token.dto.RefreshTokenRequestDto;
import com.example.test.dto.LoginDto;
import com.example.test.dto.RegisterDto;
import com.example.test.enums.TypeOfUser;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import com.example.test.repository.AuthenticationRepository;
import com.example.test.token.dto.JwtAuthenticationResponseDto;
import com.example.test.token.repository.JwtRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticationService implements AuthenticationRepository {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtRepository JwtRepository;
    private final JwtParser jwtParser;
    private final JwtValidator jwtValidator;
    private final BasketService basketService;
    private final CardService cardService;
    private final SellerService sellerService;

    @Override
    @Transactional
    public Object register(RegisterDto registerDto) {
        log.info("Received registration request for username: {}", registerDto.getUsername());
        if(registerDto.getRole().equalsIgnoreCase("Seller")){
            Seller seller = Seller.builder()
                    .sellerName(registerDto.getFirstname()+" "+registerDto.getLastname())
                    .address(registerDto.getAddress())
                    .balance(BigDecimal.valueOf(0))
                    .phoneNumber(registerDto.getPhoneNumber())
                    .status(TypeOfUser.SELLER)
                    .password(passwordEncoder.encode(registerDto.getPassword()))
                    .username(registerDto.getUsername())
                    .email(registerDto.getEmail())
                    .build();
            sellerService.save(seller);
            log.info("Successfully saved seller id:"+seller.getSellerId());
            return seller;
        }
        else {
            User user = User.builder()
                    .email(registerDto.getEmail())
                    .address(registerDto.getAddress())
                    .firstname(registerDto.getFirstname())
                    .lastname(registerDto.getLastname())
                    .phoneNumber(registerDto.getPhoneNumber())
                    .password(passwordEncoder.encode(registerDto.getPassword()))
                    .username(registerDto.getUsername())
                    .status(TypeOfUser.REGISTERED_USER)
                    .build();

            userRepository.save(user);
            basketService.save(basketService.createBasket(user));
            log.info("Before card save");
            cardService.save(user);
            log.info("User registered successfully with username: {}", user.getUsername());
            return user;
        }
    }

    @Override
    public JwtAuthenticationResponseDto login(LoginDto loginDto) {
        log.info("Received authentication request for username: {}", loginDto.getUsername());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        Optional<User> user = userRepository.findByUsername(loginDto.getUsername());
        Optional<Seller> seller = sellerService.getSellerByUsername(loginDto.getUsername());
        UserDetails userOrSeller = null;
        if (user.isEmpty())
            userOrSeller = (UserDetails) sellerService.getSellerByUsername(loginDto.getUsername()).orElseThrow();
        else if (seller.isEmpty())
            userOrSeller = userRepository.findByUsername(loginDto.getUsername()).orElseThrow();

        String jwt = JwtRepository.generateToken(userOrSeller);
        String refreshToken = JwtRepository.generateRefreshToken(new HashMap<>(), userOrSeller);

        assert userOrSeller != null;
        log.info("User authenticated successfully with email: {}", userOrSeller.getUsername());
        return JwtAuthenticationResponseDto.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken)
                .build();
    }

    public JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequest) throws JwtTokenExpiredException {
        log.info("Received refresh token request");

        String username = jwtParser.extractUsername(refreshTokenRequest.getRefreshToken());
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with this username was not found")
                );
        if(jwtValidator.isTokenExpired(refreshTokenRequest.getRefreshToken()))
            throw new JwtTokenExpiredException("Refresh token is expired");

        if(!jwtValidator.isTokenValid(refreshTokenRequest.getRefreshToken(), user))
            throw new JwtIsInvalid("Refresh token is invalid");

        String jwt = JwtRepository.generateToken(user);

        log.info("Updated access token expiration date with refresh token.");

        return JwtAuthenticationResponseDto.builder()
                .accessToken(jwt)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .build();
    }

}
