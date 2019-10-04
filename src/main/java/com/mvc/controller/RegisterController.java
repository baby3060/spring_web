package com.mvc.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.mvc.controller.validator.RegisterMemberValidator;
import com.mvc.entity.Member;
import com.mvc.service.MemberService;

import org.springframework.validation.Errors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController<memberService> {
    
    @Autowired
    private MemberService memberService;

    @GetMapping("/step1")
    public String handleStep1() {
        return "register/step1";
    }
    
    @GetMapping("/step2") 
    public String handleStpe2(@RequestParam(value = "agree", defaultValue = "false") Boolean agreeWrap, Model model){
        String result = "redirect:step1";

        if( agreeWrap ) {
            result = "register/step2";
            model.addAttribute("registerMember", new Member());
            registerModel(model);
        } 

        return result;
    }

    @PostMapping("/joinproc")
    public String joinStep3(Model model, @ModelAttribute("registerMember") Member member, Errors errors) {
    	
        new RegisterMemberValidator().validate(member, errors);

        String nextUrl = "";
        
        
        if(errors.hasErrors()) {
        	registerModel(model);
            
            model.addAttribute("member", member);
            nextUrl = "register/step2";
        } else {
            int result = memberService.regist(member);

            if( result == 1 ) {
                nextUrl = "register/completeJoin";
            } else {
            	registerModel(model);
                model.addAttribute("member", member);
                nextUrl = "register/step2";
                errors.rejectValue("email", "duplicate");
            }
        }

        return nextUrl;
    }
    
    private void registerModel(Model model) {
    	Map<String, String> loginTypes = new HashMap<String, String>();
        loginTypes.put("1", "일반회원");
        loginTypes.put("2", "기업회원");
        loginTypes.put("3", "헤드헌터회원");
        model.addAttribute("loginTypes", loginTypes);
        model.addAttribute("favoriteFoodArr", new ArrayList<String>(Arrays.asList("사과", "바나나", "포도")));
    }
    
}