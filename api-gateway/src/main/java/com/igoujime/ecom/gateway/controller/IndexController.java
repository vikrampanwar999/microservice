package com.igoujime.ecom.gateway.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.igoujime.ecom.gateway.entity.LoginRequest;

@RestController
public class IndexController {
/*
    private AuthenticationManager authenticationManager;
    @Autowired
	PasswordEncoder encoder;
    IndexController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping
    ResponseEntity index(HttpServletRequest request, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
	@PostMapping("/login")
    ResponseEntity login(@RequestBody LoginRequest loginRequest) {
    	//LoginRequest loginRequest=new LoginRequest("vik","123");
        String username = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        String encodedPassword=encoder.encode(password);
        System.out.println(encodedPassword);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder
            .getContext()
            .setAuthentication(authentication);
        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }
*/}
