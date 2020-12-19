package com.mvc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
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
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import com.mvc.entity.CUDVo;
import com.mvc.entity.Member;
import com.mvc.repository.MemberRepository;
import com.mvc.command.model.BoardSearchVO;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired 
    private SqlSessionTemplate sqlSession;
    
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
    public Object dataAjax(@RequestParam Map<String, Object> data, HttpServletRequest request) throws Exception {
    	String sqlMap = data.get("sqlmap").toString();
    	String code = data.get("code").toString();
    	
    	String paramStr = data.get("rowdata").toString();
    	
		Gson gson = new Gson();
		
		// 배열 형태가 아니라면? 하나의 메소드에서, 분기나 예외 처리하지 않고, 하나의 메소드에서 처리하고 싶어서 이렇게 배열 형태로 만들어버림.
		if( !(paramStr.startsWith("["))) {
			paramStr = "[" + paramStr;
		}
		
		if( !(paramStr.endsWith("]"))) {
			paramStr = paramStr + "]";
		}
		
		Type type = new TypeToken<ArrayList<Map<String, Object>>>() {}.getType();
		ArrayList<HashMap<String, Object>> paramList = gson.fromJson(paramStr, type);
		CUDVo cudVo = new CUDVo();
		
		cudVo.setListMap(paramList);
		
		int rtValue = -1;
		
		if( !code.equals("") ) {
        	if( code.equals("INSERT") ) {
            	rtValue = sqlSession.insert(sqlMap, cudVo);
            } else if( code.equals("UPDATE") ) {
            	rtValue = sqlSession.update(sqlMap, cudVo);
            } else if( code.equals("DELETE") ) {
            	rtValue = sqlSession.delete(sqlMap, cudVo);
            }
        }
		
    	Map<String, Object> result = new HashMap<String, Object>();
    	if( rtValue < 0 ) {
        	result.put("status", "fail");
        } else {
        	result.put("status", "success");
        }
    	
    	return gson.toJson(result);
    }
}
