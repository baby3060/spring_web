package com.mvc.entity;

import java.util.List;
import java.util.ArrayList;

public class Question {
    private String title;
    private List<String> options = new ArrayList<String>();

    public Question(String title) {
        this.title = title;
    }

    public Question(String title, List<String> options) {
        this(title);
        this.options = options;
    }

    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }

    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }

    public String toString() {
        return title + " : " + options.toString();
    }

    public boolean equals(Object obj) {
        if( obj == null ) {
            return false;
        }
        if( obj == this ) {
            return true;
        } else {
            Question temp = (Question)obj;

            return (this.title.equals(temp.getTitle()) && this.options.equals(temp.getOptions()));
        }
    }

    public int hashCode() {
        int result = 35;
        result += title.hashCode();
        result += options.hashCode();
        return result;
    }

    public boolean isChoice() {
        return ((options != null) && !options.isEmpty());
    }

}