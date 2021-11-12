package com.spring.demo.service;

import com.spring.demo.converter.AppUserConverter;
import com.spring.demo.entity.app_user.AppUser;
import com.spring.demo.entity.app_user.AppUserRequest;
import com.spring.demo.entity.app_user.AppUserResponse;
import com.spring.demo.exception.NotFoundException;
import com.spring.demo.exception.UnprocessableEntityException;
import com.spring.demo.repository.AppUserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AppUserService {
    private AppUserRepository repository;

    public AppUserService(AppUserRepository repository){
        this.repository =repository;
    }

    public AppUserResponse createUser(AppUserRequest request) {
        Optional<AppUser> existingUser = repository.findByEmailAddress(request.getEmailAddress());
        if (existingUser.isPresent()) {
            throw new UnprocessableEntityException("This email address has been used");
        }

        AppUser user = AppUserConverter.toAppUser(request);
        user = repository.insert(user);
        return AppUserConverter.toAppUserResponse(user);
    }
    public AppUserResponse  getUserResponseById(String id){
        AppUser user =repository.findById(id)
                .orElseThrow(()->new NotFoundException("Can't find user"));
        return AppUserConverter.toAppUserResponse(user);
    }

    public AppUser getUserByEmail(String email){
        AppUser user = repository.findByEmailAddress(email)
                .orElseThrow(()->new NotFoundException("Can't find User"));
        return user;

    }

    public List<AppUserResponse> getUserResponses (){
        List<AppUser> responses = repository.findAll();
        return AppUserConverter.toAppUserResponses(responses);
    }
}
