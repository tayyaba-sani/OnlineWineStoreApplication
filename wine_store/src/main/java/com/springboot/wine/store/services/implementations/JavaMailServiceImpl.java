package com.springboot.wine.store.services.implementations;


import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.common.exceptions.EmailException;
import com.springboot.wine.store.services.JavaMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class JavaMailServiceImpl implements JavaMailService {
    private final JavaMailSender mailSender;
    Logger logger = LoggerFactory.getLogger(JavaMailServiceImpl.class);

    public JavaMailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String email) {
        try {
            logger.info("Service: JavaMailServiceImpl: sendEmail: Start");
            String from = "tayyabasani26pisces@gmail.com";
            String to = email;

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(from);
            message.setTo(to);
            message.setSubject(Constants.EMAIL_SUBJECT);
            message.setText(Constants.EMAIL_BODY);

            mailSender.send(message);
            logger.info("Service: JavaMailServiceImpl: sendEmail: End");
        } catch (Exception exception) {
            throw new EmailException(Constants.EMAIL_ERROR + exception.getMessage(), this.getClass().toString());
        }
    }
}
