package com.mvc.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @RequestMapping("/step1")
    public String handleStep1(
    		@RequestParam(value = "agree", defaultValue = "false") Boolean agreeWrap
    		, @RequestParam(value = "email", required=false) String email
    		, @RequestParam(required=false) String mobile
    		, @RequestParam(required=false) String name
    		, Model model
    		) {
    	
    	model.addAttribute("agree", agreeWrap);
    	model.addAttribute("mobile", mobile);
    	model.addAttribute("email", email);
    	model.addAttribute("name", name);
    	
        return "register/step1";
    }
    
    @RequestMapping("/step2") 
    public String handleStpe2(@RequestParam(value = "agree", defaultValue = "false") Boolean agreeWrap, Model model, @RequestParam(required = false) String email, @RequestParam(required = false) String mobile, @RequestParam(required = false) String name){
        String result = "redirect:step1";

        if( agreeWrap ) {
            result = "register/step2";
            Member member = new Member();
            member.setEmail(email);
            member.setMobile(mobile);
            member.setName(name);
            
            model.addAttribute("registerMember", member);
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
        	if(memberService.countByMobile(member.getMobile()) == 0) {
        		int result = memberService.regist(member);

                if( result == 1 ) {
                    nextUrl = "register/completeJoin";
                } else {
                	registerModel(model);
                    model.addAttribute("member", member);
                    nextUrl = "register/step2";
                    errors.rejectValue("email", "duplicate");
                }
        	} else {
        		registerModel(model);
                model.addAttribute("member", member);
                nextUrl = "register/step2";
                errors.rejectValue("mobile", "duplicate");
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