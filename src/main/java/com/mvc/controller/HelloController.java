package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @RequestMapping("/main")
    public String hello(Model model, @RequestParam(value="name", required = false) String name) {
        System.out.println("HelloController");

        model.addAttribute("greeting", "Hello~" + name + "!!!!");

        return "main/index";
    }
}