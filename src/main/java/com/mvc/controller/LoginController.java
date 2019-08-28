package com.mvc.controller;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.controller.validator.LoginValidator;
import com.mvc.command.LoginCommand;
import com.mvc.entity.UserInfo;
import com.mvc.service.AuthService;
import com.mvc.service.exceptions.IDNotMatchingException;
import com.mvc.service.exceptions.NotMatchingMemberException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
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
    public String form(LoginCommand command, @CookieValue(value = "REMEMBER_EMAIL", required = false) Cookie cookie, Model model) {
        if( cookie != null ) {
            command.setEmail(cookie.getValue());
            command.setRememberEmail(true);
        }

        model.addAttribute("command", command);
        return "login/loginForm";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("command") LoginCommand command, 
                        Errors errors, 
                        Model model, 
                        HttpServletRequest request, 
                        HttpServletResponse response) {
        new LoginValidator().validate(command, errors);

        if( errors.hasErrors() ) {
            model.addAttribute("command", command);
            return "login/loginForm";
        }

        try {
            UserInfo info = authService.authenticate(command.getEmail(), command.getPassword());

            Cookie rememberCookie = new Cookie("REMEMBER_EMAIL", command.getEmail());

            rememberCookie.setPath("/");

            if( command.isRememberEmail() ) {
                // 한달 간 저장
                rememberCookie.setMaxAge(60 * 60 * 24 * 30);
            } else {
                rememberCookie.setMaxAge(0);
            }

            response.addCookie(rememberCookie);

            // HttpSession과 Request에서 getSession 가져오는 차이는 HttpSession을 받을 경우 항상 Session 객체 생성

            HttpSession session = request.getSession();
            session.setAttribute("info", info);
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