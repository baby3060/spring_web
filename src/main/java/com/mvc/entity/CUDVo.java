package com.mvc.entity;

import java.util.HashMap;
import java.util.List;

/**
 * AJAX 통해서 LIST 형태로 받을 인자들을 처리용(CUD) 
 */
public class CUDVo {
	
	private List<HashMap<String, Object>> listMap;
	
	public void setListMap(List<HashMap<String, Object>> listMap) {
    	this.listMap = listMap;
    }
    public List<HashMap<String, Object>> getListMap() {
    	return this.listMap;
    }
}
