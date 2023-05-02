package com.it.academy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendActivationEmail(String email, String activationLink) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            helper.setTo(email);
            helper.setSubject("Активация аккаунта");

            String text = "<html>" +
                    "<body>" +
                    "Благодарим Вас за регистрацию на нашем сайте" +
                    "<br>" +
                    "Для активации аккаунта нажмите на кнопку:" +
                    "<br>" +
                    "<h3><a href=\"" + activationLink + "\">" + "Активировать аккаунт" + "</a></h3>" +
                    "</body>" +
                    "</html>";

            helper.setText(text, true);
            mailSender.send(message);

        } catch (MessagingException | MailException e) {
            System.out.println(e.getMessage());
        }

    }

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        } catch (MailException e) {
            System.out.println(e.getMessage());
        }

    }

}

