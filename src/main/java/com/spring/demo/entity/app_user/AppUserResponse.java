package com.spring.demo.entity.app_user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AppUserResponse {
    private String id;
    private String emailAddress;
    private String name;
    private List<UserAuthority> authorities;
}
