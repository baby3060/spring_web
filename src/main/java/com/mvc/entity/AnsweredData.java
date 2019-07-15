package com.mvc.entity;

import java.util.List;
import java.util.ArrayList;

public class AnsweredData {
    private List<String> responses = new ArrayList<String>();
    private Respondent res = new Respondent();

    public List<String> getResponses() { return responses; }
    public void setResponses(List<String> responses) { this.responses = responses; }

    public Respondent getRes() { return res; }
    public void setRes(Respondent res) { this.res = res; }

    public boolean equals(Object obj) {
        if( obj == null ) {
            return false;
        }

        if( obj == this ) {
            return true;
        } else {
            if( obj instanceof AnsweredData) {
                AnsweredData temp = (AnsweredData)obj;

                return ((this.responses.equals(temp.responses)) && (this.res.equals(temp.res)));
            } else {
                return false;
            }
        }
    }

    public int hashCode() {
        int result = 31;
        result += responses.hashCode();
        if( res != null ) {
            result += res.hashCode();
        }
        return result;
    }
}