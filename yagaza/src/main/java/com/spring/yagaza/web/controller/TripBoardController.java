package com.spring.yagaza.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.yagaza.web.domain.TripBoard;
import com.spring.yagaza.web.service.TripBoardService;

@Controller
public class TripBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(TripBoardController.class);
	
	@Autowired
	private TripBoardService tripBoardService;
	
	@RequestMapping("/board/list")
	public String list(Model model){
		logger.info("[welcome : /board/list ]");
		model.addAttribute("tripBoardList", tripBoardService.findByBoardList());
		return "board/tripBoardList";
	}
	
	@RequestMapping("/board/view")
	public String view(Model model, Long tripBoardNo){
		logger.info("[welcome : /board/view ]");
		//String tripBoardNo = req.getParameter("tripBoardNo");
		model.addAttribute("tripBoardDetail", tripBoardService.findByBoardDetail(tripBoardNo));
		return "board/tripBoardView";
	}
	
	@RequestMapping("/board/write")
	public String write(Model model){
		logger.info("[welcome : /board/write => redirect write page~ ]");
		return "board/tripBoardWrite";
	}
	
	@RequestMapping("/board/writeEnd")
	public String writeEnd(Model model, TripBoard tripBoard){
		
		logger.info(tripBoard.getTitle());
		logger.info(tripBoard.getContent());

		tripBoardService.save(tripBoard);
		
		return "redirect:/board/list";
	}
}