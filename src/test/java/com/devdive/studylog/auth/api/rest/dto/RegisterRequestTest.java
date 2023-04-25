package com.devdive.studylog.auth.api.rest.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterRequestTest {

    private static Validator validator;

    @BeforeAll
    public static void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("회원가입 요청 입력이 형식에 맞다면 검증 성공")
    @Test
    void id_pattern_validation_success() {
        // given
        RegisterRequest request = RegisterRequest.builder()
                .email("test@test.com")
                .password("password1234")
                .nickname("nickname")
                .build();

        // when
        var violations = validator.validate(request);

        // then
        assertThat(violations).isEmpty();
    }

    @DisplayName("비밀번호 길이가 8자리 미만이면 요청 실패")
    @Test
    void invalid_short_password_length_fail() {
        // given
        RegisterRequest request = RegisterRequest.builder()
                .email("test@test.com")
                .password("p")
                .nickname("nickname")
                .build();

        // when
        var violations = validator.validate(request);

        // then
        assertThat(violations).isNotEmpty();
    }

    @DisplayName("비밀번호 길이가 20자리 초과이면 요청 실패")
    @Test
    void invalid_long_password_length_fail() {
        // given
        RegisterRequest request = RegisterRequest.builder()
                .email("test@test.com")
                .password("a".repeat(30))
                .nickname("nickname")
                .build();

        // when
        var violations = validator.validate(request);

        // then
        assertThat(violations).isNotEmpty();
    }

}
