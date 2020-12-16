package com.mvc.command.model;

import com.mvc.entity.Level;

public class BoardSearchVO {
    private String field;
    private Level Level;
    private String searchText;
    public void setField(String field) {
        this.field = field;
    }
    
    public String getField() {
        return field;
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