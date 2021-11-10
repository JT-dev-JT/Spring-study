package com.spring.demo.config;

import com.spring.demo.service.MailService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:mail.properties")
@Getter
public class MailConfig {
    @Value("${mail.platform}")
    private String platform;

    @Value("${mail.auth.enabled}")
    private boolean authEnabled;

    @Value("${mail.starttls.enabled}")
    private  boolean starttlsEnabled;

    @Value("${mail.protocol}")
    private String protocol;

    @Value("${mail.gmail.host}")
    private String gmailHost;

    @Value("${mail.gmail.port}")
    private int gmailPort;

    @Value("${mail.gmail.username}")
    private String gmailUsername;

    @Value("${mail.gmail.password}")
    private String gmailPassword;

    @Value("${mail.yahoo.host}")
    private String yahooHost;

    @Value("${mail.yahoo.port}")
    private int yahooPort;

    @Value("${mail.yahoo.username}")
    private String yahooUsername;

    @Value("${mail.yahoo.password}")
    private String yahooPassword;

    public static final String GMAIL_SERVICE = "gmailService";
    public static final String YAHOO_MAIL_SERVICE = "yahooMailService";


    @Bean
    public MailService mailService(){
        JavaMailSenderImpl mailSender = "gmail".equals(platform)
                ? gmailSender()
                :yahooSender();

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", authEnabled);
        props.put("mail.smtp.starttls.enable", starttlsEnabled);
        props.put("mail.transport.protocol", protocol);
        return new MailService(mailSender);

    }


    public JavaMailSenderImpl gmailSender()  {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(gmailHost);
        mailSender.setPort(gmailPort);
        mailSender.setUsername(gmailUsername);
        mailSender.setPassword(gmailPassword);
        return mailSender;

    }

    public JavaMailSenderImpl yahooSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(yahooHost);
        mailSender.setPort(yahooPort);
        mailSender.setUsername(yahooUsername);
        mailSender.setPassword(yahooPassword);

        return mailSender;
    }

}
