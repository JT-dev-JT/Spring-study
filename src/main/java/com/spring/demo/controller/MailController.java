package com.spring.demo.controller;

import com.spring.demo.config.MailConfig;
import com.spring.demo.entity.SendMailRequest;
import com.spring.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.awt.*;

@RestController
@RequestMapping(value = "/mail",produces =  MediaType.APPLICATION_JSON_VALUE)
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping
    public ResponseEntity<Void> sendMail (@Valid @RequestBody SendMailRequest request){
        mailService.sendMail(request);
        return ResponseEntity.noContent().build();
    }

}
