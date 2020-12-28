package com.mvc.command.model;

import com.mvc.entity.Level;

public class BoardSearchVO {
    private String field;
    private Level Level;
    private String searchText;
    private int noticeLength;
    private int length;
    private int start;
    private int initLength;
    
	public int getInitLength() {
		return initLength;
	}

	public void setInitLength(int initLength) {
		this.initLength = initLength;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setField(String field) {
        this.field = field;
    }
    
    public String getField() {
        return field;
    }

	public int getNoticeLength() {
		return noticeLength;
	}

	public void setNoticeLength(int noticeLength) {
		this.noticeLength = noticeLength;
	}

	public Level getLevel() {
		return Level;
	}

	public void setLevel(Level level) {
		Level = level;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

}