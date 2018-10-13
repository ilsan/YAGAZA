package com.spring.yagaza.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@Controller
@RequestMapping("/imageBoard")
public class ImageController {

    @RequestMapping("/imageBoardList")
    public String imgBoardList(WebRequest request, Model model){
        log.info("[welcome : /imageBoard/imageBoardList => imageBoardList page~ ]");

        return "imgBoard/imgList";
    }

    @RequestMapping("/imageBoardWrite")
    public String write(WebRequest request, Model model){
        log.info("[welcome : /imageBoard/imageBoardWrite => imageBoardWrite page~ ]");

        return "imgBoard/imgWrite";
    }
}
