package com.jt.demo.service;

import com.jt.demo.aop.ActionType;
import com.jt.demo.aop.EntityType;
import com.jt.demo.aop.SendEmail;
import com.jt.demo.entity.app_user.AppUser;
import com.jt.demo.entity.app_user.AppUserRequest;
import com.jt.demo.entity.app_user.AppUserResponse;
import com.jt.demo.repository.AppUserRepository;
import com.jt.demo.converter.AppUserConverter;
import com.jt.demo.exception.NotFoundException;
import com.jt.demo.exception.UnprocessableEntityException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

public class AppUserService {

    private AppUserRepository repository;
    private BCryptPasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository repository) {
        this.repository = repository;
        this.passwordEncoder=new BCryptPasswordEncoder();
    }
    @SendEmail(entity = EntityType.APP_USER,action = ActionType.CREATE)
    public AppUserResponse createUser(AppUserRequest request) {
        Optional<AppUser> existingUser = repository.findByEmailAddress(request.getEmailAddress());
        if (existingUser.isPresent()) {
            throw new UnprocessableEntityException("This email address has been used.");
        }

        AppUser user = AppUserConverter.toAppUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = repository.insert(user);
        return AppUserConverter.toAppUserResponse(user);
    }

    public AppUserResponse getUserResponseById(String id) {
        AppUser user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find user."));

        return AppUserConverter.toAppUserResponse(user);
    }

    public AppUser getUserByEmail(String email) {
        return repository.findByEmailAddress(email)
                .orElseThrow(() -> new NotFoundException("Can't find user."));
    }

    public List<AppUserResponse> getUserResponses() {
        List<AppUser> users = repository.findAll();
        return AppUserConverter.toAppUserResponses(users);
    }

}
