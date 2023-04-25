package com.devdive.studylog.auth.api.rest.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "이메일 형식에 맞지 않습니다.")
        String email,
        @Size(min = 8, max = 20)
        String password,
        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(min = 2, max = 14, message = "닉네임의 길이는 2 ~ 15 사이입니다.")
        String nickname
) {
}
