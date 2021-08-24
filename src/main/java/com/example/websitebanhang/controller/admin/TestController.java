package com.example.websitebanhang.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {

		//return "login";
		return "email/email-template-thymeleaf";
	}
	
	@GetMapping("/user")
	public String userAccess() {
		return "login";
	}

	@GetMapping("/mod")
	public String moderatorAccess() {
		return "test.txt";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
