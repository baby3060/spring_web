package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @RequestMapping("/main")
    public String hello(Model model, @RequestParam(value="name", required = false) String name) {

        model.addAttribute("greeting", "Hello~Guest!!!!");

        return "main/index";
    }
}