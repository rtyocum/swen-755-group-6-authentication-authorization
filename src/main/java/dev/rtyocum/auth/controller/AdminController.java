package dev.rtyocum.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

	@GetMapping("/admin")
	public String adminEndpoint() {
		return "This is a admin endpoint.";
	}
}
