package com.devdive.studylog.auth.service;


import com.devdive.studylog.user.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static org.assertj.core.api.Assertions.assertThat;

class JwtServiceTest {

    @Value("{$auth.jwt.secret_key}")
    String secretKey;
    JwtService jwtService;

    @BeforeEach
    void beforeEach() {
        jwtService = new JwtService();
    }

    Claims parse(String token) {
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        Keys.hmacShaKeyFor(keyByte);
        return Jwts.parserBuilder()
                .setSigningKey(keyByte)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @DisplayName("회원의 이메일 정보를 가지고 JWT를 생성")
    @Test
    void givenUser_whenGenerateJwt_thenParseClaimsWithSameValue() {
        User user = User.builder()
                .email("test@test.com")
                .password("password")
                .nickname("nickname")
                .build();

        String token = jwtService.generateToken(user);

        assertThat(parse(token).getSubject()).isEqualTo(user.getEmail());
    }



}
