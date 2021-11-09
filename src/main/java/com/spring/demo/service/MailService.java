package com.spring.demo.service;

import com.spring.demo.config.MailConfig;
import com.spring.demo.entity.SendMailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Service
public class MailService {

    @Autowired
    MailConfig mailConfig;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private JavaMailSenderImpl mailSender;

    @PostConstruct
    private void init(){
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfig.getHost());
        mailSender.setPort(mailConfig.getPort());
        mailSender.setUsername(mailConfig.getUsername());
        mailSender.setPassword(mailConfig.getPassword());

        Properties prop = mailSender.getJavaMailProperties();
        prop.put("mail.smtp.auth",mailConfig.isAuthEnabled());
        prop.put("mail.smtp.starttls.enable",mailConfig.isStarttlsEnabled());
        prop.put("mail.transport.protocal",mailConfig.getProtocol());

    }

    public void sendMail (SendMailRequest request){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailConfig.getUsername());
        message.setTo(request.getReceivers());
        message.setSubject(request.getSubject());
        message.setText(request.getContent());

        try {
            mailSender.send(message);
        }catch (MailAuthenticationException ex){
            LOGGER.error(ex.getMessage());
        }catch (Exception ex){
            LOGGER.warn(ex.getMessage());
        }

    }



}
