package com.spring.yagaza.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.yagaza.web.domain.User;
import com.spring.yagaza.web.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mainpage() {
		return "index";
	}
	
	@RequestMapping("/join")
	public String join() {
		return "join";
	}
	
	@RequestMapping("/joinSuccess")
	public String joinSuccess(User user) {
		
		userService.Useradd(user);
		
		System.out.println("==========");
		System.out.println(user);
		System.out.println("==========");
		
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("idcheck")
	public void idcheck(String userId, HttpServletResponse respornse) throws IOException {
    	System.out.println("탔냐~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	int result = userService.idCheck(userId);
    	PrintWriter out = respornse.getWriter();
    	System.out.println("결과  : ~~~~~~~~~~~~~~~~~ "  + result);
    	out.println(result);
		
	}
	
}
