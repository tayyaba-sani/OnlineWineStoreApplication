package com.springboot.wine.store.services.implementations;


import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.common.exceptions.EmailException;
import com.springboot.wine.store.services.JavaMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class JavaMailServiceImpl implements JavaMailService {

    private JavaMailSender mailSender;

    public JavaMailServiceImpl(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public void sendEmail(String email)
    {
        try {
            String from = "tayyabasani26pisces@gmail.com";
            String to = email;

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(from);
            message.setTo(to);
            message.setSubject(Constants.EMAIL_SUBJECT);
            message.setText(Constants.EMAIL_BODY);

            mailSender.send(message);
        }
        catch (Exception exception)
        {
            throw new EmailException(Constants.EMAIL_ERROR + exception.getMessage(),this.getClass().toString());
        }
    }
}
