package com.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mvc.command.ChangePwdCommand;
import com.mvc.command.LoginCommand;
import com.mvc.controller.validator.ChangePwdValidator;
import com.mvc.entity.Member;
import com.mvc.entity.UserInfo;
import com.mvc.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/member/changepwd")
public class ChangePwdController {
    
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String form(@ModelAttribute("changeCommand") ChangePwdCommand command, Model model) {
        model.addAttribute("changeCommand", command);
        return "member/changePwdForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String changePwd(@ModelAttribute("changeCommand") ChangePwdCommand command, Errors errors, HttpServletRequest req, Model model) {
        new ChangePwdValidator().validate(command, errors);

        if( errors.hasErrors() ) {
            model.addAttribute("command", command);
            return "member/changePwdForm";
        }

        HttpSession session = req.getSession(); 
        
        UserInfo info = (UserInfo)session.getAttribute("info");
        if( (info.getEmail() != null) && (!info.getEmail().equals(""))) {
            if( memberService.count(info.getEmail()) == 1) {
                Member member = memberService.selectMember(info.getEmail());
                if( member.getPassword().equals(command.getCurrentPassword()) ) {
                    memberService.updatePassword(member, command.getNewPassword());
                    return "member/changedPwd";
                } else {
                    model.addAttribute("command", command);
                    errors.rejectValue("currentPassword", "NotMatching.currentPassword");
                    return "member/changePwdForm";
                }
            } else {
                String[] args = {info.getEmail()};
                errors.reject("NOMember", args, "");
                return "redirect:/main";
            }
        } else {
            return "redirect:/login";
        }
    }
}