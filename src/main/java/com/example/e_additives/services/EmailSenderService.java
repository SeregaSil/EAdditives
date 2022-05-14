package com.example.e_additives.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String Email, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Email);
        message.setTo(Email);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
