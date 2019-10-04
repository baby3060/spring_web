package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.mvc.entity.*;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    @GetMapping
    public ModelAndView form(Model model) {
        AnsweredData ansData = new AnsweredData();
        Respondent res = new Respondent();
        ansData.setRes(res);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("survey/form");
        mv.addObject("questions", createQuestions());
        mv.addObject("answerData", ansData);
        return mv;
    }

    private List<Question> createQuestions() {
        Question q1 = new Question("당신의 역할은 무엇입니까?", new ArrayList<String>(Arrays.asList("서버", "프론트", "풀스택")));
        Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?", new ArrayList<String>(Arrays.asList("Eclipse", "IntelliJ", "SublimeText")));
        Question q3 = new Question("하고 싶은 말을 적어주세요!");

        return new ArrayList<Question>(Arrays.asList(q1, q2, q3));
    }

    @PostMapping
    public String submit(@ModelAttribute("ansData") AnsweredData ansData) {
        return "survey/report";
    }
}