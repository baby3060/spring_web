package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.entity.*;

@Controller
@RequestMapping("/survey")
public class SurveyController {
    @RequestMapping(method = RequestMethod.GET)
    public String form(Model model) {
        AnsweredData ansData = new AnsweredData();
        Respondent res = new Respondent();
        ansData.setRes(res);

        model.addAttribute("answerData", ansData);
        return "survey/form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("ansData") AnsweredData ansData) {
        return "survey/report";
    }
}