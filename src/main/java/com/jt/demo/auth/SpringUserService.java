package com.jt.demo.auth;

import com.jt.demo.entity.app_user.AppUser;
import com.jt.demo.exception.NotFoundException;
import com.jt.demo.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpringUserService implements UserDetailsService {
    @Autowired
    AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try {
            AppUser user = appUserService.getUserByEmail(username);
            List<SimpleGrantedAuthority> authorities =user.getAuthorities().stream()
                    .map(auth-> new SimpleGrantedAuthority(auth.name()))
                    .collect(Collectors.toList());
            return new User(user.getName(),user.getPassword(), authorities);
        }catch(NotFoundException ex){
            throw new UsernameNotFoundException("Username is not wrong");
        }
    }
}
