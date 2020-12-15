package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/main.do")
    public String hello(Model model, @RequestParam(value="name", required = false) String name) {

        model.addAttribute("greeting", "Hello~Guest!!!!");

        return "main/index";
    }
}