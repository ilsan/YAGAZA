package com.spring.yagaza.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.yagaza.web.service.TestService;

@Controller
public class TripBoardController {
	
	@Autowired
	private TestService test;
	
	@RequestMapping("/")
	public String test(Model model){
		model.addAttribute("list",test.List());
		return "index";

	}
}
