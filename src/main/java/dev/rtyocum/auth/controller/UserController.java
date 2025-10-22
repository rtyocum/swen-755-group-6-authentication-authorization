package dev.rtyocum.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping("/user")
	public String userEndpoint() {
		return "This is a user endpoint.";
	}
}
