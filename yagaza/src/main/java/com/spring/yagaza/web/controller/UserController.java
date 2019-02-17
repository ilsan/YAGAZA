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
	public String joinSuccess(User user, Model model) {

		model.addAttribute("user", user);
		
		userService.Useradd(user);
		
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping("idCheck")
	public void idcheck(String id, HttpServletResponse respornse) throws IOException {
    	System.out.println("탔냐~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	Long result = userService.idCheck(id);
    	PrintWriter out = respornse.getWriter();
    	System.out.println("결과  : ~~~~~~~~~~~~~~~~~ "  + result);
    	out.println(result);
	}
	
}
