package com.devdive.studylog.config;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MessageConfigTest {

    @Autowired
    private MessageSource validationMessageSource;
    private MessageSourceAccessor messageSourceAccessor;

    private static Validator validator;

    @BeforeAll
    public static void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void BeforeEach() {
        messageSourceAccessor = new MessageSourceAccessor(validationMessageSource);
    }

    @Test
    void givenLocale_whenGetMessage_thenRightMessage() {
        Locale locale = Locale.KOREA;

        String message = messageSourceAccessor.getMessage("email.blank", locale);

        assertThat(message).isEqualTo("이메일을 입력해주세요.");
    }
}
