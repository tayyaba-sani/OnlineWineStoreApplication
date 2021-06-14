package com.springboot.wine.store.services.implementations;

import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.services.JavaMailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class JavaMailServiceImplTest {

    @Autowired
    JavaMailService javaMailService;

    @Mock
    private JavaMailSender mailSender;

    @BeforeEach
    void setUp() {
        javaMailService = new JavaMailServiceImpl(mailSender);
    }

    @Test
    void sendEmail() {
        String to = "tayyaba.sani@yahoo.com";
        javaMailService.sendEmail(to);

    }

    @Test
    void sendEmailThrowException() {
        String to = "tayyaba.sani@yahoo.com";
        doNothing().when(mailSender).send(new SimpleMailMessage());
        assertThatThrownBy(() -> javaMailService.sendEmail(to)).hasMessageContaining(Constants.EMAIL_ERROR);

    }
}