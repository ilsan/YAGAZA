package com.spring.yagaza.web.controller;

import com.spring.yagaza.web.domain.User;
import com.spring.yagaza.web.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value="/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/join")
	public String join(User user) {

		userService.userAdd(user);
		
		return "redirect:/";
	}
	
	@ResponseBody
	@GetMapping("/idCheck")
	public ResponseEntity<?> idCheck(String id) {
		return ResponseEntity.ok(userService.idCheck(id));
	}
}