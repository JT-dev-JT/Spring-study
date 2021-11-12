package com.spring.demo.entity.app_user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
@Getter
@Setter
public class AppUserRequest {
    @NotBlank
    private String emailAddress;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotEmpty
    private List<UserAuthority> authorities;
}
