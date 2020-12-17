package com.mvc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mvc.entity.Member;
import com.mvc.repository.MemberRepository;
import com.mvc.command.model.BoardSearchVO;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping(value="/get_list", method=RequestMethod.POST)
    @ResponseBody
    public String ajaxCall(@ModelAttribute("search") BoardSearchVO search ) {
        Gson gson = new Gson();
        
        Map<String,Object> dataMaps = new HashMap<String,Object>();
        
        List<Member> list = memberRepository.listBasic();
        long countAll = memberRepository.countAll();
        dataMaps.put("data", list);
        dataMaps.put("recordsTotal", countAll);
        dataMaps.put("recordsFiltered", countAll);

        return gson.toJson(dataMaps);
    }

    @RequestMapping(value="/data_ajax", method=RequestMethod.POST)
    @ResponseBody
    public Object dataAjax(@RequestParam Map<String, Object> data, HttpServletRequest request) {
    	Gson gson = new Gson();
    	Map<String, Object> result = new HashMap<String, Object>();
    	
    	System.out.println(data.toString());
    	result.put("status", "success");
    	
    	return gson.toJson(result);
    }
}
