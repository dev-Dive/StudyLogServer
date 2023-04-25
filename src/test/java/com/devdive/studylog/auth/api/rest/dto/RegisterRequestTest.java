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

    @DisplayName("이메일이 빈 문자열이면 요청 실패")
    @Test
    void blank_email_fail() {
        RegisterRequest request = RegisterRequest.builder()
                .email("")
                .password("password")
                .nickname("nickname")
                .build();

        var violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
    }

    @DisplayName("이메일이 형식에 맞지 않다면 요청 실패")
    @Test
    void invalid_pattern_email_fail() {
        RegisterRequest request = RegisterRequest.builder()
                .email("test")
                .password("password")
                .nickname("nickname")
                .build();

        var violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
    }

    @DisplayName("닉네임이 빈 문자열이면 요청 실패")
    @Test
    void blank_nickname_fail() {
        RegisterRequest request = RegisterRequest.builder()
                .email("test@test.com")
                .password("password")
                .nickname("")
                .build();

        var violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
    }

    @DisplayName("닉네임이 너무 짧으면 요청 실패")
    @Test
    void short_nickname_fail() {
        RegisterRequest request = RegisterRequest.builder()
                .email("test@test.com")
                .password("password")
                .nickname("1")
                .build();

        var violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
    }

    @DisplayName("닉네임이 너무 길면 요청 실패")
    @Test
    void long_nickname_fail() {
        RegisterRequest request = RegisterRequest.builder()
                .email("test@test.com")
                .password("password")
                .nickname("a".repeat(15))
                .build();

        var violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
    }

    @DisplayName("닉네임에 한글, 영어, 숫자 외의 문자가 있으면 요청 실패")
    @Test
    void invalid_character_in_nickname_fail() {
        RegisterRequest request = RegisterRequest.builder()
                .email("test@test.com")
                .password("password")
                .nickname("***")
                .build();

        var violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
    }

}
