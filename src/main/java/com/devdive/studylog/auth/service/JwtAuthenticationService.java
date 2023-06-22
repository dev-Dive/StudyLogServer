package com.devdive.studylog.auth.service;

import com.devdive.studylog.auth.api.rest.dto.AuthenticationRequest;
import com.devdive.studylog.auth.api.rest.dto.AuthenticationResponse;
import com.devdive.studylog.auth.api.rest.dto.RegisterRequest;
import com.devdive.studylog.user.domain.User;
import com.devdive.studylog.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Primary
@Service
@RequiredArgsConstructor
public class JwtAuthenticationService implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        userRepository.findByEmail(request.email())
                .ifPresent(user -> {
                    log.info("{} is already registered", user.getEmail());
                    throw new IllegalArgumentException(user.getEmail());
                });

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .nickname(request.nickname())
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        );
        authenticationManager.authenticate(authentication);
        User user = userRepository.findByEmail(request.password())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
