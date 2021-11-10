package com.spring.demo.service;

import com.spring.demo.entity.SendMailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MailService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final JavaMailSenderImpl mailSender;
    private final long tag;
    private final List<String> mailMessages;
    private final String LOG_EMAIL;


    public MailService(JavaMailSenderImpl mailSender){
        this.mailSender=mailSender;
        this.tag=System.currentTimeMillis();
        this.mailMessages=new ArrayList<>();
        this.LOG_EMAIL=mailSender.getUsername();
    }

    public void sendMail (SendMailRequest request) {
        sendMail(request.getSubject(),request.getContent(),request.getReceivers() );
    }

    public void sendMail (String subject, String content, List<String> receiver){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSender.getUsername());
        message.setTo(receiver.toArray(new String[0]));
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
        }catch (MailAuthenticationException ex){
            LOGGER.error(ex.getMessage());
        }catch (Exception ex){
            LOGGER.warn(ex.getMessage());
        }

    }

    public void sendNewProductMail(String productId){
        String contenet= String.format("There's a new created product (%s).", productId);
        sendMail("New Product", contenet, Collections.singletonList(LOG_EMAIL));
    }

    private void printMessage(){
        System.out.println("#############");
        System.out.printf("Spring Boot is about to destroy Mail Service $d.\n\n",tag);
    }
}
