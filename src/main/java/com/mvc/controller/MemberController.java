package com.mvc.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mvc.entity.Member;
import com.mvc.entity.UserInfo;
import com.mvc.command.ChangePwdCommand;
import com.mvc.command.SearchCommand;
import com.mvc.controller.validator.ChangePwdValidator;
import com.mvc.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/list")
    public String list(@ModelAttribute("dCommand") SearchCommand searchCommand, Model model) {

        List<Member> memberList = new ArrayList<Member>();

        if( searchCommand.getSearch() == null || searchCommand.getSearch().equals("") ) {
            searchCommand.setSearch("N");
        }

        // 호출할 때 마다 메소드 실행 시 디비에 너무 많이 접근
        if( searchCommand.getSearch().equalsIgnoreCase("Y")) {
            memberList = memberService.getListBasic(searchCommand.getFrom(), searchCommand.getTo());
        }

        model.addAttribute("memberList", memberList);

        return "member/memberList";
    }

    @GetMapping("/changepwd")
    public String form(@ModelAttribute("changeCommand") ChangePwdCommand command, Model model) {
        model.addAttribute("changeCommand", command);
        return "member/changePwdForm";
    }

    @PostMapping("/changepwd")
    public String changePwd(@ModelAttribute("changeCommand") ChangePwdCommand command, Errors errors, HttpServletRequest req, Model model) {
        new ChangePwdValidator().validate(command, errors);

        if( errors.hasErrors() ) {
            model.addAttribute("command", command);
            return "member/changePwdForm";
        }

        HttpSession session = req.getSession(); 
        
        UserInfo info = (UserInfo)session.getAttribute("info");
        if( (info.getEmail() != null) && (!info.getEmail().equals(""))) {
            if( memberService.count(info.getMemberSeq()) == 1) {
                Member member = memberService.selectMember(info.getMemberSeq());
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