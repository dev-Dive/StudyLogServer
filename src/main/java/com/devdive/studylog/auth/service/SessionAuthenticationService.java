package com.devdive.studylog.auth.service;

import com.devdive.studylog.auth.api.rest.dto.AuthenticationRequest;
import com.devdive.studylog.auth.api.rest.dto.AuthenticationResponse;
import com.devdive.studylog.auth.api.rest.dto.RegisterRequest;
import com.devdive.studylog.user.domain.User;
import com.devdive.studylog.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
//@Primary
@RequiredArgsConstructor
public class SessionAuthenticationService implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .nickname(request.nickname())
                .build();
        this.userRepository.save(user);
        return AuthenticationResponse.builder()
                .token("session_id")
                .build();
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        );
        authenticationManager.authenticate(authentication);
        User user = userRepository.findByEmail(request.password())
                .orElseThrow();
        return AuthenticationResponse.builder()
                .token("session_id")
                .build();
    }
}
