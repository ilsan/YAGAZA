package com.spring.yagaza.web.controller;

import javax.servlet.http.HttpServletRequest;

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
	public String list(Model model){
		model.addAttribute("tripBoardList", tripBoardService.findByBoardList());
		return "board/tripBoardList";
	}
	
	@RequestMapping("/board/view")
	public String view(Model model, HttpServletRequest req){
		String tripBoardNo = req.getParameter("tripBoardNo");
		model.addAttribute("tripBoardDetail", tripBoardService.findByBoardDetail(tripBoardNo));
		return "board/tripBoardView";
	}
	
	@RequestMapping("/board/write")
	public String write(Model model){
		return "board/tripBoardWrite";
	}
}