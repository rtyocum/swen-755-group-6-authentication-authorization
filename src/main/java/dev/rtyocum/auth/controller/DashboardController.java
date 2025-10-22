package dev.rtyocum.auth.controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DashboardController {
        @GetMapping("/")
        public String home(Authentication auth) {
            String welcome_msg = "Welcome, " + auth.getName() + "! You're logged in as a "
                    + auth.getAuthorities() + ".";
            return welcome_msg;
        }
}
