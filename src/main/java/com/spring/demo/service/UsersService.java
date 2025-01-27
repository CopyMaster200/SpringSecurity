package com.spring.demo.service;

import com.spring.demo.model.Users;
import com.spring.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    AuthenticationManager authManger;
    @Autowired
    JwtService jwtService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String verify(Users user) {
        Authentication auth = authManger
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        return auth.isAuthenticated() ? jwtService.generateToken(user.getUsername()) : "Fail";
    }
}
