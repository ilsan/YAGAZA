package com.spring.yagaza.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.yagaza.web.service.TripBoardService;

@Controller
public class TripBoardController {
	
	@Autowired
	private TripBoardService tripBoardService;
	
	@RequestMapping("/board/list")
	public String test(Model model){
		
		model.addAttribute("TripBoradList", tripBoardService.findByBoardList());
		return "board/test";

	}
}
