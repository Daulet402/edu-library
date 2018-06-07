package kz.edu.sdu.diploma.implementation;

import kz.edu.sdu.diploma.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String recipient, String sender, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(title);
        message.setFrom(sender);
        message.setTo(recipient);
        message.setText(text);
        javaMailSender.send(message);
    }
}
