package dev.rtyocum.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

	@GetMapping("/public")
	public String publicEndpoint() {
		return "This is a public endpoint.";
	}
}
