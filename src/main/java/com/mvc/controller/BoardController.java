package com.mvc.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.command.BoardCommand;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    
    @RequestMapping("/list")
    public String boardList(Model model) {
        return "board/board_list";
    }

    @GetMapping("/{boardNo}")
    public String boardDetail(@PathVariable("boardNo") long boardNo, Model model) {
        return "board/boardDetail";
    }


    @PostMapping("/add")
    public String boardAdd(@ModelAttribute("command") BoardCommand boardContent
                        , Errors errors
                        , Model model
                        , HttpServletRequest request
                        , HttpServletResponse response) {
        
        
        return "board/boardList";
    }

}