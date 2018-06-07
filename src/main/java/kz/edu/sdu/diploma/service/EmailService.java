package kz.edu.sdu.diploma.service;

public interface EmailService {

    void sendMail(String recipient, String sender, String title, String text);
}
