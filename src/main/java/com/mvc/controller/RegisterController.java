package com.mvc.controller;

import com.mvc.entity.Member;
import com.mvc.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController<memberService> {
    
    @Autowired
    private MemberService memberService;

    @RequestMapping("/step1")
    public String handleStep1() {
        return "register/step1";
    }
    
    @RequestMapping(value = "/step2") 
    public String handleStpe2(@RequestParam(value = "agree", defaultValue = "false") Boolean agreeWrap, Model model){
        String result = "redirect:step1";

        if( agreeWrap ) {
            result = "register/step2";
            model.addAttribute("registerMember", new Member());
        } 

        return result;
    }

    @RequestMapping(value = "/joinproc", method = RequestMethod.POST)
    public String joinStep3(Model model, @ModelAttribute("registerMember") Member member) {
        int result = memberService.regist(member);

        String nextUrl = "";
        
        if( result == 1 ) {
            nextUrl = "register/completeJoin";
        } else {
            model.addAttribute("member", member);
            nextUrl = "register/step2";
        }

        return nextUrl;
    }
}