package com.igoujime.ecom.gateway.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/login")
    public String login() {
        return "login.html";
    }
	@RequestMapping("/logout")
	public String logout(HttpSession s) {
		s.invalidate();
		return "";
	}
}
