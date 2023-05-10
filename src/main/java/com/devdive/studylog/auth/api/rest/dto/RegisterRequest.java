package com.devdive.studylog.auth.api.rest.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "이메일 형식에 맞지 않습니다.")
        String email,
        @Size(min = 8, max = 20, message = "비밀번호의 길이는 {min}~{max} 사이입니다.")
        String password,
        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(min = 2, max = 14, message = "닉네임의 길이는 {min}~{max} 사이입니다.")
        @Pattern(regexp = "^[a-zA-Z가-힇0-9]+$", message = "닉네임은 한글, 영어, 숫자만 사용할 수 있습니다.")
        String nickname
) {
}
