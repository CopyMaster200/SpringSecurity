package com.spring.demo.service;

import com.spring.demo.model.UserPrincipal;
import com.spring.demo.model.Users;
import com.spring.demo.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if (Objects.isNull(user)) {
            log.error("User not found!!!");
            throw new UsernameNotFoundException("User not found!!!");
        }

        return new UserPrincipal(user);
    }
}
