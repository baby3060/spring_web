package com.mvc.controller;

import com.mvc.controller.validator.LoginValidator;
import com.mvc.entity.LoginCommand;
import com.mvc.entity.UserInfo;
import com.mvc.service.AuthService;
import com.mvc.service.exceptions.IDNotMatchingException;
import com.mvc.service.exceptions.NotMatchingMemberException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    };

    @RequestMapping(method = RequestMethod.GET)
    public String form(LoginCommand command, Model model) {
        model.addAttribute("command", command);
        return "login/loginForm";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("command") LoginCommand command, Errors errors, Model model) {
        new LoginValidator().validate(command, errors);

        if( errors.hasErrors() ) {
            model.addAttribute("command", command);
            return "login/loginForm";
        }

        try {
            UserInfo info = authService.authenticate(command.getEmail(), command.getPassword());

            // Session에 info 저장
            model.addAttribute("info", info);

            return "login/loginSuccess";
        } catch(IDNotMatchingException idme) {
            model.addAttribute("command", command);
            errors.reject("IDPasswordNotMatching");
            return "login/loginForm";
        } catch(NotMatchingMemberException nme) {
            String[] args = {command.getEmail()};
            model.addAttribute("command", command);
            errors.reject("NOMember", args, "");
            return "login/loginForm";
        }
    }

    
}