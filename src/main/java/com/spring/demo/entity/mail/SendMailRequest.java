package com.spring.demo.entity.mail;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class SendMailRequest {

    @NotEmpty
    private String subject;

    @NotEmpty
    private String content;

    @NotEmpty
    private List<String> receivers;



}
