package com.spring.yagaza.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.spring.yagaza.web.domain.User;
import com.spring.yagaza.web.service.UserService;

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
	public String joinSuccess(User user) {

		userService.Useradd(user);
		
		return "redirect:/";
	}
	
	@ResponseBody
	@GetMapping("/idCheck")
	public String idCheck(String id) {
    	userService.idCheck(id);
		return "잘된다";
	}
	
}
