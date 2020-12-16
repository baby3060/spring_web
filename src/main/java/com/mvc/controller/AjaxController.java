package com.mvc.controller;

import java.util.Map;
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
    public void dataAjax(@RequestParam(value="seqChk", required=false) List<String> values) {
        System.out.println(values.size());

    }
}
