package com.eCommerce.service;

import com.eCommerce.dto.LoginDto;
import com.eCommerce.dto.SignUpDto;
import com.eCommerce.entity.Role;
import com.eCommerce.entity.User;
import com.eCommerce.repo.RoleRepo;
import com.eCommerce.repo.UserRepo;
import com.eCommerce.secuity.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    UserRepo userRepo;

    RoleRepo roleRepo;

    PasswordEncoder passwordEncoder;

    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;



    @Autowired
    public AuthService(UserRepo userRepo, RoleRepo roleRepo, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder=passwordEncoder;

    }

    public String login(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.userName(), loginDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);


        String token = jwtTokenProvider.generateToken(authenticate);
        return token;
    }

    public String signup(SignUpDto signUpDto) {
        if (userRepo.existsByUserName(signUpDto.userName()))
        {
            throw new RuntimeException("User Already Present with this UserName");
        }

        User user= User.builder().userName(signUpDto.userName()).password(passwordEncoder.encode(signUpDto.password())).build();
        Role role=roleRepo.findByRoleName("user").get();

        user.setRole(Set.of(role));

        userRepo.save(user);

        return "User Created SuccessFully";
    }
}
